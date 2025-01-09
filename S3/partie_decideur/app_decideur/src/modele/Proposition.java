package modele;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public abstract class Proposition implements Iproposition {
    private int id;
	private String titre;
	private float budget;
	private int id_thematique;
	private String description;
	private Date date_publication;
    private Map<Integer, Integer> votes;
    private float indice_satisfaction;
    private int choixGagnant;

    public Proposition(int id, String titre, String description, Date date_publication, float budget, int id_thematique) {
    	this.id=id;
    	this.titre = titre;
    	this.description=description;
    	this.date_publication=date_publication;
        this.budget = budget;
        this.id_thematique=id_thematique;
        this.votes = new HashMap<>();
        String requete = """
				SELECT I.id_internaute, id_choix
        		FROM infos_membre I
        		LEFT JOIN vote_membre V ON V.id_internaute = I.id_internaute AND V.id_groupe = I.id_groupe
        		WHERE id_vote=(SELECT id_vote FROM vote WHERE id_proposition = ?);""";
        ResultSet res = Connexion.exec1Requete(Application.co,requete,id);
		try {
			while (res.next()) {
				int id_internaute = res.getInt(1);
				int id_choix = res.getInt(2);
				votes.put(id_internaute, id_choix);
                this.choixGagnant=choixGagnant();
			}
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
    }
	public String getDescription() {return description;}
    public String getTitre() {return titre;}
	public float getBudget() {return budget;}
    public Map<Integer, Integer> getVotes() {return votes;}
    
    public void afficherVotes() {
    	System.out.println("id_choix ; id_internaute");
    	for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
            System.out.println(entry.getValue() + " ; " + entry.getKey());
        }
    }

    public String toString() {
        return titre + " : Budget = " + budget + " ; Thematique = "+id_thematique;
    }

    private int nbMembre() {
    	return votes.size();
    }
    
    /**
     * Retourne l'identifiant du choix gagnant (celui avec le plus de votes).
     * En cas d'égalité, retourne -1.
     * @return L'ID du choix gagnant ou -1 en cas d'égalité.
     */
    public int choixGagnant() {
        int idChoixGagnant = -1;
        boolean egalite = false;
        Map<Integer, Integer> counting = new HashMap<>();

        this.votes.forEach((key, value) -> {
            counting.merge(value, 1, Integer::sum);
        });

        int maxVotes = 0;
        for (Map.Entry<Integer, Integer> entry : counting.entrySet()) {
            int idChoix = entry.getKey();
            int nbVotes = entry.getValue();

            if (nbVotes > maxVotes) {
                maxVotes = nbVotes;
                idChoixGagnant = idChoix;
                egalite = false;
            } else if (nbVotes == maxVotes) {
                egalite = true;
            }
        }

        if (egalite) {
            idChoixGagnant = -1;
        }

        return idChoixGagnant;
    }
	public int getIdThematique(){return this.id_thematique;}
    public int getChoixGagnant() {return choixGagnant;}
    /**
     * Calcule le nombre de membre satisfaits par la proposition.
     * @return Le nombre total de membres satisfaits.
     */
	public int nbSatisfaits() {
	    ArrayList<Integer> satisfaits = new ArrayList<>();
    	int choixGagnant = choixGagnant();
        for(Map.Entry<Integer, Integer> entree : getVotes().entrySet()) {
        	if(entree.getValue()==choixGagnant && !(satisfaits.contains(entree.getKey()))) {
        		satisfaits.add(entree.getKey());
        	}
        }
	    return satisfaits.size();
    }
    public abstract float getIndiceSucces(int ratioBudget, int ratioSucces, int ratioVotants, float budgetMax);

}