package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;// import javax.swing.ImageIcon;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tous le groupe
 */
public class Groupe {
	private int id;
    private String nom;
    private float budget;
    private int nombreDeMembres;
    private String couleur;
    private ArrayList<Proposition> propositions;
    private ArrayList<Thematique>  thematiques;
	private static final int SEUIL_GLOUTON_UN = 15;
	private static final int SEUIL_GLOUTON_DEUX = 10;
	
    public Groupe(int id) {
    	this.id=id;
        String requete = "SELECT nom_groupe, budget, COUNT(*), couleur_groupe FROM groupe G\r\n"
        		+ "INNER JOIN infos_membre IM ON IM.id_groupe = G.id_groupe\r\n"
        		+ "WHERE IM.id_groupe=? GROUP BY G.id_groupe,nom_groupe,budget;";
        ResultSet res = Connexion.exec1Requete(Application.co,requete,id);
		try {
			while (res.next()) {
				String nom = res.getString(1);
				this.nom=nom;
				float budget = res.getFloat(2);
				this.budget=budget;
				int nbMembres = res.getInt(3);
				this.nombreDeMembres=nbMembres;
				String couleur = res.getString(4);
				this.couleur = couleur;
			}
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		this.propositions=initialisationPropositionss(id);
		this.thematiques=initialisationThematiques(id);
    }

    private ArrayList<Proposition> initialisationPropositionss(int id){
    	ArrayList<Proposition> propositions = new ArrayList<>();
        String requete = """
			SELECT P.id_proposition, titre_proposition, description_proposition, date_publication, budget, id_thematique, id_scrutin
        		FROM proposition P
        		LEFT JOIN vote V ON V.id_proposition = P.id_proposition
        		WHERE id_groupe = ? AND date_fin_vote < CURDATE()""";
        ResultSet res = Connexion.exec1Requete(Application.co,requete,id);
		try {
			while (res.next()) {
				int typeScrutin = res.getInt(7);
				switch (typeScrutin) {
					case (1):
						propositions.add(new PourContre(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getFloat(5), res.getInt(6)));
						break;
					case (2):
						propositions.add(new ChoixMultiples(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getFloat(5), res.getInt(6)));
						break;
					case (3):
						propositions.add(new MajoritaireDeuxTours(res.getInt(1), res.getString(2), res.getString(3), res.getDate(4), res.getFloat(5), res.getInt(6)));
				}
				
			}
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		return propositions;
    }

    private ArrayList<Thematique> initialisationThematiques(int id){
    	ArrayList<Thematique> thematiques = new ArrayList<>();
        String requete = "SELECT id_thematique, nom_thematique, budget_thematique\r\n"
        		+ "FROM thematique\r\n"
        		+ "NATURAL JOIN theme_groupe\r\n"
        		+ "WHERE id_groupe = ?";
        ResultSet res = Connexion.exec1Requete(Application.co,requete,id);
		try {
			while (res.next()) {
				thematiques.add(new Thematique(res.getInt(1),res.getString(2),res.getFloat(3)));
			}
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		return thematiques;
    }
    
    // Getters et setters
    public int getId() {return id;}
    public String getNom() {return nom;}
    public float getBudget() {return budget;}
    public int getNombreDeMembres() {return nombreDeMembres;}
	public String getCouleur() {return couleur;}
    public ArrayList<Proposition> getPropositions() {return propositions;}
    
	public static float budgetTotal(ArrayList<Proposition> propositions) {
		float total=0;
		for (Proposition p : propositions) {
			total+=p.getBudget();
		}
		return total;
	}

	public ArrayList<Thematique> getThematiques() {
		return thematiques;
	}

	/**
	 * Vérifier si le budget de la thématique de la proposition passé en paramètre
	 * n'est pas dépassé
	 * @param combinaison La combinaison de proposition actuelle.
	 * @param actuelle la combinaison dont on veut vérifier la thématique.
	 * @return True si le budget n'est pas dépasé. Faux sinon.
	 * @author Micha Martin
	 */
	private boolean budgetThematiqueOk(ArrayList<Proposition> combinaison,Proposition actuelle) {
		float budgetThematique=0;
		for(Thematique t : thematiques) {
			if(t.getId()==actuelle.getIdThematique()) {
				budgetThematique=t.getBudget();
			}
		}
		budgetThematique-=actuelle.getBudget();
		for(Proposition p : combinaison) {
			if(p.getIdThematique()==actuelle.getIdThematique()) {
				budgetThematique-=p.getBudget();
			}
		}
		return budgetThematique>=0;
	}
	
	/**
	 * Maximiser le nombre de membres satisfaits et minimiser le budget.
	 * @return La liste optimale de propositions.
	 * @throws IllegalArgumentException Si les entrées sont invalides (liste vide ou budget insuffisant).
	 * @author Micha Martin
	 */
	public ArrayList<Proposition> optimiserBudgetEtSatisfaction() {
		if (propositions == null || propositions.isEmpty()) {
	        throw new IllegalArgumentException("Aucune proposition.");
	    }
	    if (budget <= 0) {
	        throw new IllegalArgumentException("Le budget du groupe doit être strictement positif.");
	    }
	    ArrayList<Proposition> combinaison;
	    int nbPropositions=propositions.size();
	    if(nbPropositions>SEUIL_GLOUTON_UN) {
	    	combinaison=gloutonDeuxOptiBudgetSatisfaction();
	    }else if(nbPropositions >SEUIL_GLOUTON_DEUX) {
	    	combinaison=gloutonUnOptiBudgetSatisfaction();
	    }else {
	    	combinaison=forceBruteOptiBudgetSatisfaction(propositions, new ArrayList<>(), budget, 0);
	    }
	    return combinaison;
	}
	
	/**
	 * Fonction récursive pour trouver la combinaison optimale.
	 * @param propositions La liste des propositions du groupe.
	 * @param combinaisonCourante La combinaison actuelle.
	 * @param budgetGroupeRestant Le budget restant du groupe.
	 * @param index L'index courant dans la liste des propositions.
	 * @return La combinaison optimale.
	 * @author Micha Martin
	 */
	private ArrayList<Proposition> forceBruteOptiBudgetSatisfaction(
		    ArrayList<Proposition> propositions,
		    ArrayList<Proposition> combinaisonCourante,
		    float budgetGroupeRestant,
		    int index
		) {
		    if (index == propositions.size()) {
		        return new ArrayList<>(combinaisonCourante);
		    }

		    ArrayList<Proposition> sansProposition = forceBruteOptiBudgetSatisfaction(
		        propositions, combinaisonCourante, budgetGroupeRestant, index + 1);

		    Proposition actuelle = propositions.get(index);
		    ArrayList<Proposition> avecProposition = new ArrayList<>();
		    if ((budgetGroupeRestant >= actuelle.getBudget())&&(budgetThematiqueOk(combinaisonCourante,actuelle))) {
		        combinaisonCourante.add(actuelle);
		        avecProposition = forceBruteOptiBudgetSatisfaction(
		            propositions, combinaisonCourante, budgetGroupeRestant - actuelle.getBudget(), index + 1);
		        combinaisonCourante.remove(combinaisonCourante.size() - 1);
		    }

		    int satisfactionAvec = satisfactionTotale(avecProposition);
		    int satisfactionSans = satisfactionTotale(sansProposition);
		    
		    if (satisfactionAvec > satisfactionSans) {
		        return avecProposition;
		    } else if (satisfactionAvec == satisfactionSans) {
		        return budgetTotal(avecProposition) < budgetTotal(sansProposition) ? avecProposition : sansProposition;
		    } else {
		        return sansProposition;
		    }
		}

	/**
	 * Fonction gloutonne pour trouver la combinaison optimale en
	 * priorisant le nombre de membres satisfait par une proposition
	 * @return La combinaison optimale.
	 * @author Micha Martin
	 */
	public ArrayList<Proposition> gloutonUnOptiBudgetSatisfaction() {
	    ArrayList<Proposition> resultat = new ArrayList<>();

	    // Tri des propositions par indice de satisfaction décroissant, puis par budget croissant
	    propositions.sort((p1, p2) -> {
	        if (p1.nbSatisfaits() != p2.nbSatisfaits()) {
	            return Float.compare(p2.nbSatisfaits(), p1.nbSatisfaits());
	        }
	        return Float.compare(p1.getBudget(), p2.getBudget());
	    });

	    // Budget global restant
	    float budgetRestantGlobal = budget;

	    // Sélection gloutonne des propositions
	    for (Proposition p : propositions) {
	        float budgetProposition = p.getBudget();

	        // Vérification des contraintes de budget global et thématique
	        if (budgetRestantGlobal >= budgetProposition && budgetThematiqueOk(resultat, p)) {
	            // Ajout de la proposition à la sélection
	            resultat.add(p);
	            budgetRestantGlobal -= budgetProposition;
	        }
	        if(satisfactionTotale(resultat)==p.nbMembre()) {
	        	break;
	        }
	    }

	    return resultat;
	}

	/**
	 * Fonction gloutonne pour trouver la combinaison optimale en
	 * priorisant l'indice de satisfaction d'une proposition
	 * égal au nombre de membres satisfaits divisé par le nombres de membres du groupe
	 * @return La combinaison optimale.
	 * @author Micha Martin
	 */
	public ArrayList<Proposition> gloutonDeuxOptiBudgetSatisfaction() {
	    ArrayList<Proposition> resultat = new ArrayList<>();

	    // Tri des propositions par ratio satisfaction/budget décroissant
	    propositions.sort((p1, p2) -> {
	        float ratio1 = (p1.nbSatisfaits()/getNombreDeMembres()) / p1.getBudget();
	        float ratio2 = (p2.nbSatisfaits()/getNombreDeMembres()) / p2.getBudget();
	        if (ratio1 != ratio2) {
	            return Float.compare(ratio2, ratio1); // Tri par ratio décroissant
	        }
	        return Float.compare(p1.getBudget(), p2.getBudget()); // Tri par budget croissant en cas d'égalité
	    });

	    // Budget global restant
	    float budgetRestantGlobal = budget;

	    // Sélection gloutonne des propositions
	    for (Proposition p : propositions) {
	        float budgetProposition = p.getBudget();

	        // Vérification des contraintes de budget global et thématique
	        if (budgetRestantGlobal >= budgetProposition && budgetThematiqueOk(resultat, p)) {
	            // Ajout de la proposition à la sélection
	            resultat.add(p);
	            budgetRestantGlobal -= budgetProposition;
	        }
	        if(satisfactionTotale(resultat)==p.nbMembre()) {
	        	break;
	        }
	    }

	    return resultat;
	}
	
	/**
	 * Calcule le nombre de membres satisfaits par la combinaison donnée
	 * @param combinaison La combinaison de propositions.
	 * @return Le nombre total de membres satisfaits.
	 * @author Micha Martin
	 */
	public static int satisfactionTotale(ArrayList<Proposition> combinaison) {
	    ArrayList<Integer> satisfaits = new ArrayList<>();
	    for (Proposition p : combinaison) {
	    	int choixGagnant = p.choixGagnant();
	        for(Map.Entry<Integer, Integer> entree : p.getVotes().entrySet()) {
	        	if(entree.getValue()==choixGagnant && !(satisfaits.contains(entree.getKey()))) {
	        		satisfaits.add(entree.getKey());
	        	}
	        }
	    }
	    return satisfaits.size();
	}

	/**
	* Résumé : maximiser le nombre d'utilisateur satisfait
	* Description : cette fonction cherche à Maximiser la proportion d’utilisateurs ayant un vote satisfait en choisissant un projet 
	* pour chaque thématique. En fonction de la taille de la donnée, l'algortithme choisi n'est pas le même
	* @param combinaisonCourante la liste de combinaison en cours d'execution
	* @param index occurence au sein de la liste de propostion en cours
	* @return un ArrayList contenant la liste trier selon l'algorithme choisi
	* @author Naherry Mohamed Daroueche
	 */
	 public ArrayList<Proposition> maxProportionThematique( 
	 	ArrayList<Proposition> combinaisonCourante, 
	 	int index
	 ) {
		ArrayList<Proposition> resultat=new ArrayList<>();
	 	if (this.propositions.size()>10) {
	 		resultat=gloutonUnMaxProportionThematique();
	 	}
	 	else if(this.propositions.size()>5) {
	 		resultat=gloutonDeuxMaxProportionThematique();
	 	}
	 	else {
	 		resultat=forceBrutMaxProportionThematique(combinaisonCourante,index);
	 	}
	 	return resultat;
	 }

	/**
	 * Résumé : maximiser le nombre d'utilisateur satisfait en prenant un par thématique
	 * Description : Fonction qui cherche à maximiser le nombre d'utilisateur satisfait en prenant une proposition par thématique
	 * Pour trouver cette meilleur combinaison, on utilise la force brut
	 * @param combinaisonCourante
	 * @param index
	 * @return un ArrayList contenant les proposition choisi
	 * @author Naherry Mohamed Daroueche
	 */
	private ArrayList<Proposition> forceBrutMaxProportionThematique(
		ArrayList<Proposition> combinaisonCourante, 
		int index
	) 
	{
		if (index == this.propositions.size()) {
			return combinaisonCourante;
		}

		ArrayList<Proposition> meilleureCombinaison = new ArrayList<>(combinaisonCourante);

		for (int i = index; i < this.propositions.size(); i++) {
			Proposition proposition = this.propositions.get(i);
			boolean thematiqueDejaChoisie = false;

			for (Proposition p : combinaisonCourante) {
				if (p.getIdThematique() == proposition.getIdThematique()) {
					thematiqueDejaChoisie = true;
					break;
				}
			}

			if (!thematiqueDejaChoisie) {
				combinaisonCourante.add(proposition);
				ArrayList<Proposition> nouvelleCombinaison = forceBrutMaxProportionThematique(combinaisonCourante, i + 1);
				if (satisfactionTotale(nouvelleCombinaison) > satisfactionTotale(meilleureCombinaison)) {
					meilleureCombinaison = nouvelleCombinaison;
				}
				combinaisonCourante.remove(combinaisonCourante.size() - 1);
			}
		}

		return meilleureCombinaison;
	}

	/**
	 * Résumé : maximiser le nombre d'utilisateur satisfait en prenant un par thématique
	 * Description : Fonction qui cherche à maximiser le nombre d'utilisateur satisfait en prenant une proposition par thématique
	 * Pour trouver cette meilleur combinaison, on utilise un algorithme glouton qui a pour critère la propostion avec le plus grand indice de succès
	 * @return un ArrayList contenant les proposition choisi
	 * @author Naherry Mohamed Daroueche
	 */
	 private ArrayList<Proposition> gloutonUnMaxProportionThematique() {
		  ArrayList<Proposition> propositionChoisi = new ArrayList<>();
		  for (Thematique thematique : this.thematiques) {
			  float thematiquePlusIndiceSatisfaction = 0;
			  Proposition propositionDeDepart = this.propositions.get(0);
			  for (Proposition proposition : this.propositions) {
				  if (proposition.getIdThematique()==thematique.getId()) {
					  float thematiqueCourante = proposition.getIndiceSatisfaction();
					  if (thematiqueCourante>thematiquePlusIndiceSatisfaction) {
						  thematiquePlusIndiceSatisfaction = thematiqueCourante;
						  propositionDeDepart = proposition;
					  }
				  }
			  }
			  propositionChoisi.add(propositionDeDepart);
  
		  }
		  return propositionChoisi;
	 }
	
	/**
	 * Résumé : maximiser le nombre d'utilisateur satisfait en prenant un par thématique
	 * Description : Fonction qui cherche à maximiser le nombre d'utilisateur satisfait en prenant une proposition par thématique
	 * Pour trouver cette meilleur combinaison, on utilise un algorithme glouton qui a pour critère la propostion avec le plus grand nombre de votant différent
	 * @return un ArrayList contenant les proposition choisi
	 * @author Naherry Mohamed Daroueche
	 */
	private ArrayList<Proposition> gloutonDeuxMaxProportionThematique() {
		ArrayList<Proposition> propositionChoisi = new ArrayList<>();
		for (Thematique thematique : this.thematiques) {
			int thematiquePlusDeVote = 0;
			Proposition propositionDeDepart = this.propositions.get(0);
			for (Proposition proposition : this.propositions) {
				if (proposition.getIdThematique()==thematique.getId()) {
					int thematiqueCourante = proposition.getVotes().size();
					if (thematiqueCourante>thematiquePlusDeVote) {
						thematiquePlusDeVote = thematiqueCourante;
						propositionDeDepart = proposition;
					}
				}
			}
			propositionChoisi.add(propositionDeDepart);

		}
		return propositionChoisi;
	}

	/**
	 * @author Jakub Potaczala
	 */
	public ArrayList<Proposition> lePlusDePropositionsParThematique() {
        ArrayList<Proposition> resultat=new ArrayList<>();
        try {
    		if (this.getPropositions().size() > SEUIL_GLOUTON_DEUX) {
                resultat=lePlusDePropositionsParThematiqueGlouton();
            }else {
                resultat=lePlusDePropositionsParThematiqueBruteForce();
            }
        }catch(Exception e) {
        	System.out.println("Erreur : "+e.getMessage());
        }
        return resultat;
    }

	/**
	 * @author Jakub Potaczala
	 */
    public ArrayList<Proposition> lePlusDePropositionsParThematiqueGlouton() {
        ArrayList<Proposition> resultats = new ArrayList<>();
        Map<Integer, Float> budgetRestantParThematique = new HashMap<>();

        // Initialiser le budget disponible pour chaque thématique
        for (Thematique thematique : this.getThematiques()) {
            budgetRestantParThematique.put(thematique.getId(), thematique.getBudget());
        }

        // Grouper les propositions par thématique
        Map<Integer, ArrayList<Proposition>> propositionsParThematique = new HashMap<>();
        for (Proposition proposition : this.getPropositions()) {
            int idThematique = proposition.getIdThematique();
            propositionsParThematique
                .computeIfAbsent(idThematique, k -> new ArrayList<>())
                .add(proposition);
        }

        // Pour chaque thématique, ajouter les propositions dans la limite du budget
        for (Map.Entry<Integer, ArrayList<Proposition>> entry : propositionsParThematique.entrySet()) {
            int idThematique = entry.getKey();
            ArrayList<Proposition> propositions = entry.getValue();
            float budgetRestant = budgetRestantParThematique.get(idThematique);

            // Trier les propositions par coût croissant pour maximiser leur nombre
            propositions.sort(Comparator.comparingDouble(Proposition::getBudget));

            for (Proposition proposition : propositions) {
                if (proposition.getBudget() <= budgetRestant) {
                    resultats.add(proposition);
                    budgetRestant -= proposition.getBudget();
                }
            }

            // Mettre à jour le budget restant pour la thématique
            budgetRestantParThematique.put(idThematique, budgetRestant);
        }

        return resultats;
    }

	/**
	 * @author Jakub Potaczala
	 */
    public ArrayList<Proposition> lePlusDePropositionsParThematiqueBruteForce() {
        ArrayList<Proposition> toutesLesPropositions = this.getPropositions();
        int n = toutesLesPropositions.size();

        // Préparer les budgets restants par thématique
        Map<Integer, Float> budgetRestantParThematique = new HashMap<>();
        for (Thematique thematique : this.getThematiques()) {
            budgetRestantParThematique.put(thematique.getId(), thematique.getBudget());
        }

        ArrayList<Proposition> meilleureCombinaison = new ArrayList<>();
        int maxPropositions = 0;

        // Générer et vérifier toutes les combinaisons
        for (int i = 0; i < (1 << n); i++) { // 2^n combinaisons
            ArrayList<Proposition> combinaison = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    combinaison.add(toutesLesPropositions.get(j));
                }
            }

            if (estValide(combinaison, budgetRestantParThematique) && combinaison.size() > maxPropositions) {
                maxPropositions = combinaison.size();
                meilleureCombinaison = combinaison;
            }
        }

        return meilleureCombinaison;
    }

	/**
	 * @author Jakub Potaczala
	 */
    static private boolean estValide(ArrayList<Proposition> combinaison, Map<Integer, Float> budgetParThematique) {
        Map<Integer, Float> budgetRestant = new HashMap<>(budgetParThematique);
        for (Proposition proposition : combinaison) {
            int idThematique = proposition.getIdThematique();
            float budget = budgetRestant.getOrDefault(idThematique, 0f);
            if (proposition.getBudget() > budget) {
                return false;
            }
            budgetRestant.put(idThematique, budget - proposition.getBudget());
        }
        return true;
    }

	/**
	 * @author Jakub Potaczala
	 */
	public ArrayList<Proposition> selectionnerMeilleuresPropositions(float budgetMaxParThematique, int ratioBudget, int ratioSucces, int ratioVotants) {
		ArrayList<Proposition> resultats = new ArrayList<>();
		Map<Integer, Float> budgetRestantParThematique = new HashMap<>();
	
		// Initialiser le budget disponible pour chaque thématique
		for (Thematique thematique : this.getThematiques()) {
			budgetRestantParThematique.put(thematique.getId(), Math.min(thematique.getBudget(), budgetMaxParThematique));
		}
	
		// Grouper les propositions par thématique
		Map<Integer, ArrayList<Proposition>> propositionsParThematique = new HashMap<>();
		for (Proposition proposition : this.getPropositions()) {
			int idThematique = proposition.getIdThematique();
			propositionsParThematique
				.computeIfAbsent(idThematique, k -> new ArrayList<>())
				.add(proposition);
			
		}
	
		// Sélectionner les meilleures propositions par thématique
		for (Map.Entry<Integer, ArrayList<Proposition>> entry : propositionsParThematique.entrySet()) {
			int idThematique = entry.getKey();
			ArrayList<Proposition> propositions = entry.getValue();
			float budgetRestant = budgetRestantParThematique.get(idThematique);
	
			// Trier les propositions par indice de succès décroissant
			propositions.sort((p1, p2) -> {
				float indiceP1 = p1.getIndiceSucces(ratioBudget, ratioSucces, ratioVotants, budgetMaxParThematique);
				float indiceP2 = p2.getIndiceSucces(ratioBudget, ratioSucces, ratioVotants, budgetMaxParThematique);
				return Float.compare(indiceP2, indiceP1); // Décroissant
			});
	
			// Ajouter les propositions dans la limite du budget
			for (Proposition proposition : propositions) {
				if (proposition.getBudget() <= budgetRestant) {
					resultats.add(proposition);
					budgetRestant -= proposition.getBudget();
				}
			}
	
			// Mettre à jour le budget restant pour la thématique
			budgetRestantParThematique.put(idThematique, budgetRestant);
		}
	
		return resultats;
	}
}