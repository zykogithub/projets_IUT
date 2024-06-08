package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Discipline représente une discipline sportive.
 * Elle contient des informations sur le lieu associé à la discipline ainsi que la liste des épreuves qui lui sont liées.
 * Chaque épreuve est associée à une discipline.
 *
 * @author Yousra HACHMI
 */
public class Discipline implements Serializable {

    private Lieu sonLieu; // Le lieu associé à cette discipline
    private ArrayList<Epreuve> sesEpreuves; // La liste des épreuves associées à cette discipline
    private int identifiant; // L'identifiant de la discipline
    private String nom; // Le nom de la discipline

    /**
     * Constructeur pour créer une nouvelle discipline.
     *
     * @param id L'identifiant de la discipline.
     * @param nom Le nom de la discipline.
     */
    public Discipline(int id, String nom) {
        this.identifiant = id;
        this.nom = nom;
        this.sesEpreuves = new ArrayList<>();
    }

    /**
     * Méthode pour ajouter une épreuve à la liste des épreuves de cette discipline.
     *
     * @param e L'épreuve à ajouter.
     */
    public void ajouterEpreuve(Epreuve e) {
        sesEpreuves.add(e);
        System.out.println("L'épreuve : " + e.getNom() + " a été ajoutée dans la discipline " + this.nom);
    }

    /**
     * Méthode pour modifier le nom de la discipline.
     *
     * @param nouveauNom Le nouveau nom de la discipline.
     */
    public void modifierDiscipline(String nouveauNom) {
        this.nom = nouveauNom;
        System.out.println("La discipline a été modifiée avec le nouveau nom : " + nouveauNom);
    }

    /**
     * Méthode pour supprimer une épreuve de la liste des épreuves de cette discipline.
     *
     * @param idEpreuve L'identifiant de l'épreuve à supprimer.
     */
    public void supprimerEpreuve(int idEpreuve) {
        for (int i = 0; i < sesEpreuves.size(); i++) {
            if (sesEpreuves.get(i).getIdentifiant() == idEpreuve) {
                sesEpreuves.remove(i);
                System.out.println("L'épreuve avec l'identifiant " + idEpreuve + " a été supprimée de la discipline " + this.nom);
                return;
            }
        }
        System.out.println("Aucune épreuve trouvée avec l'identifiant " + idEpreuve + " dans la discipline " + this.nom);
    }

    // Méthodes getters pour obtenir les informations de la discipline

    /**
     * Méthode pour obtenir l'identifiant de la discipline.
     *
     * @return L'identifiant de la discipline.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir le nom de la discipline.
     *
     * @return Le nom de la discipline.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la liste des épreuves associées à cette discipline.
     *
     * @return La liste des épreuves associées à cette discipline.
     */
    public ArrayList<Epreuve> getSesEpreuves() {
        return sesEpreuves;
    }
}
