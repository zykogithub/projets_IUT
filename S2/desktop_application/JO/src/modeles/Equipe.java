package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente une équipe participant aux Jeux Olympiques de 2024.
 * Elle contient des informations sur l'équipe telles que le nom, l'année de création et les joueurs qui la composent.
 *
 * @author Yousra HACHMI
 */
public class Equipe implements Serializable {

    ArrayList<Athlete> sesAthletes;
    Pays sonPays;
    ArrayList<Session> sesSessions;
    ClassementSession sonClassement;
    Epreuve sonEpreuve;
    private int identifiant;
    private String nom;
    private int anneeCreation;

    /**
     * Constructeur pour créer une équipe avec les informations initiales.
     *
     * @param id              L'identifiant de l'équipe.
     * @param nom             Le nom de l'équipe.
     * @param anneeCreation   L'année de création de l'équipe.
     */
    public Equipe(int id, String nom, int anneeCreation) {
        this.identifiant = id;
        this.nom = nom;
        this.anneeCreation = anneeCreation;
        this.sesAthletes = new ArrayList<>();
        this.sesSessions = new ArrayList<>();
    }

    /**
     * Méthode pour ajouter un joueur à l'équipe.
     *
     * @param joueur Le joueur à ajouter à l'équipe.
     */
    public void ajouterJoueurEquipe(Athlete joueur) {
        sesAthletes.add(joueur);
    }

    /**
     * Méthode pour supprimer un joueur de l'équipe par identifiant et nom.
     *
     * @param identifiant L'identifiant du joueur à supprimer.
     * @param nom         Le nom du joueur à supprimer.
     */
    public void supprimerJoueur(int identifiant, String nom) {
        for (int i = 0; i < sesAthletes.size(); i++) {
            Athlete joueur = sesAthletes.get(i);
            if (joueur.getIdentifiant() == identifiant && joueur.getNom().equals(nom)) {
                sesAthletes.remove(i);
                System.out.println("Le joueur " + nom + " a été supprimé de l'équipe.");
                return; // Sortie de la boucle après la suppression du joueur
            }
        }
        // Si aucun joueur correspondant n'est trouvé
        System.out.println("Aucun joueur correspondant trouvé.");
    }

    /**
     * Méthode pour modifier les détails de l'équipe.
     *
     * @param nom             Le nouveau nom de l'équipe.
     * @param anneeCreation   La nouvelle année de création de l'équipe.
     */
    public void modifierEquipe(String nom, int anneeCreation) {
        this.nom = nom;
        this.anneeCreation = anneeCreation;
    }

    /**
     * Méthode pour obtenir l'identifiant de l'équipe.
     *
     * @return L'identifiant de l'équipe.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir le nom de l'équipe.
     *
     * @return Le nom de l'équipe.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir l'année de création de l'équipe.
     *
     * @return L'année de création de l'équipe.
     */
    public int getAnneeCreationEquipe() {
        return anneeCreation;
    }

    /**
     * Méthode pour obtenir la liste des athlètes de l'équipe.
     *
     * @return La liste des athlètes de l'équipe.
     */
    public ArrayList<Athlete> getSesAthletes() {
        return sesAthletes;
    }

    /**
     * Méthode pour obtenir la liste des sessions auxquelles l'équipe participe.
     *
     * @return La liste des sessions de l'équipe.
     */
    public ArrayList<Session> getSesSessions() {
        return sesSessions;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAnneeCreation(int anneeCreation) {
        this.anneeCreation = anneeCreation;
    }
    
}
