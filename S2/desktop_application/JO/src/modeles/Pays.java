package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente un pays participant aux Jeux Olympiques de 2024.
 * Elle contient des informations sur le pays telles que le nom, l'identifiant, les équipes et les athlètes qui en font partie.
 * Un pays peut avoir plusieurs équipes et plusieurs athlètes.
 * 
 * @author Yousra HACHMI
 */
public class Pays implements Serializable {

    ArrayList<Equipe> sesEquipes;
    ArrayList<Athlete> sesAthletes;
    private String nom;
    private int identifiant;

    /**
     * Constructeur pour créer un pays avec les informations initiales.
     *
     * @param id  L'identifiant du pays.
     * @param nom Le nom du pays.
     */
    public Pays(int id, String nom) {
        this.identifiant = id;
        this.nom = nom;
        this.sesEquipes = new ArrayList<>();
        this.sesAthletes = new ArrayList<>();
    }

    /**
     * Méthode pour ajouter une équipe au pays.
     *
     * @param equipe L'équipe à ajouter.
     */
    public void ajouterEquipe(Equipe equipe) {
        sesEquipes.add(equipe);
        System.out.println("L'équipe " + equipe.getNom() + " a été ajoutée au pays " + this.nom);
    }

    /**
     * Méthode pour ajouter un athlète au pays.
     *
     * @param joueur L'athlète à ajouter.
     */
    public void ajouterAthlete(Athlete joueur) {
        sesAthletes.add(joueur);
        System.out.println("L'athlète " + joueur.getNom() + " a été ajouté au pays " + this.nom);
    }

    /**
     * Méthode pour supprimer une équipe du pays par identifiant.
     *
     * @param idEquipe L'identifiant de l'équipe à supprimer.
     */
    public void supprimerEquipe(int idEquipe) {
        for (int i = 0; i < sesEquipes.size(); i++) {
            if (sesEquipes.get(i).getIdentifiant() == idEquipe) {
                System.out.println("L'équipe " + sesEquipes.get(i).getNom() + " a été supprimée du pays " + this.nom);
                sesEquipes.remove(i);
                return;
            }
        }
        System.out.println("Aucune équipe trouvée avec l'identifiant " + idEquipe + " pour le pays " + this.nom);
    }

    /**
     * Méthode pour supprimer un athlète du pays par identifiant.
     *
     * @param idAthlete L'identifiant de l'athlète à supprimer.
     */
    public void supprimerAthlete(int idAthlete) {
        for (int i = 0; i < sesAthletes.size(); i++) {
            if (sesAthletes.get(i).getIdentifiant() == idAthlete) {
                System.out.println("L'athlète " + sesAthletes.get(i).getNom() + " a été supprimé du pays " + this.nom);
                sesAthletes.remove(i);
                return;
            }
        }
        System.out.println("Aucun athlète trouvé avec l'identifiant " + idAthlete + " pour le pays " + this.nom);
    }

    /**
     * Méthode pour obtenir le nom du pays.
     *
     * @return Le nom du pays.
     */
    public String getNomPays() {
        return nom;
    }

    /**
     * Méthode pour obtenir l'identifiant du pays.
     *
     * @return L'identifiant du pays.
     */
    public int getIdentifiantPays() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir la liste des équipes du pays.
     *
     * @return La liste des équipes du pays.
     */
    public ArrayList<Equipe> getSesEquipes() {
        return sesEquipes;
    }

    /**
     * Méthode pour obtenir la liste des athlètes du pays.
     *
     * @return La liste des athlètes du pays.
     */
    public ArrayList<Athlete> getSesAthletes() {
        return sesAthletes;
    }
}
