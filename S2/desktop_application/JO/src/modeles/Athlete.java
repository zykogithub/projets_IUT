package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Athlete représente un athlète participant aux Jeux Olympiques.
 * Elle contient des informations sur l'athlète telles que son identifiant, son nom, sa date de naissance,
 * son historique de médailles, sa biographie, ainsi que les sessions auxquelles il participe.
 *
 * @author Yousra HACHMI
 */
public class Athlete implements Serializable {

    // Les attributs représentant les associations et les informations personnelles de l'athlète.

    Pays sonPays; // Le pays auquel l'athlète appartient
    ArrayList<Session> sesSessions; // Les sessions auxquelles l'athlète participe
    ClassementEpreuve sonClassement; // Le classement de l'athlète dans une épreuve
    Equipe sonEquipe; // L'équipe à laquelle l'athlète appartient
    Epreuve sonEpreuve; // L'épreuve à laquelle l'athlète participe
    private int identifiant; // L'identifiant unique de l'athlète
    private String prenom; // Le prénom de l'athlète
    private String nom; // Le nom de l'athlète
    private String dateNaissance; // La date de naissance de l'athlète
    private String historiqueMedaille; // L'historique des médailles de l'athlète
    private String biographie; // La biographie de l'athlète

    /**
     * Constructeur pour créer un nouvel athlète.
     *
     * @param id L'identifiant unique de l'athlète.
     * @param prenom Le prénom de l'athlète.
     * @param nom Le nom de l'athlète.
     * @param dateNaissance La date de naissance de l'athlète.
     * @param historiqueMedaille L'historique des médailles de l'athlète.
     * @param biographie La biographie de l'athlète.
     */
    public Athlete(int id, String prenom, String nom, String dateNaissance, String historiqueMedaille, String biographie) {
        this.identifiant = id;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.historiqueMedaille = historiqueMedaille;
        this.biographie = biographie;
        this.sesSessions = new ArrayList<>();
    }

    /**
     * Méthode pour ajouter une session à l'athlète.
     *
     * @param session La session à ajouter.
     */
    public void ajouterSession(Session session) {
        sesSessions.add(session);
        System.out.println("La session avec l'identifiant " + session.getIdentifiant() + " a été ajoutée à l'athlète " + this.nom);
    }

    /**
     * Méthode pour supprimer une session de l'athlète par identifiant.
     *
     * @param idSession L'identifiant de la session à supprimer.
     */
    public void supprimerSession(int idSession) {
        for (int i = 0; i < sesSessions.size(); i++) {
            if (sesSessions.get(i).getIdentifiant() == idSession) {
                sesSessions.remove(i);
                System.out.println("La session avec l'identifiant " + idSession + " a été supprimée de l'athlète " + this.nom);
                return;
            }
        }
        System.out.println("Aucune session trouvée avec l'identifiant " + idSession + " pour l'athlète " + this.nom);
    }

    /**
     * Méthode pour modifier les informations de l'athlète.
     *
     * @param prenom Le nouveau prénom de l'athlète.
     * @param nom Le nouveau nom de l'athlète.
     * @param dateNaissance La nouvelle date de naissance de l'athlète.
     * @param historiqueM Le nouvel historique des médailles de l'athlète.
     * @param biographie La nouvelle biographie de l'athlète.
     */
    public void modifierAthlete(String prenom, String nom, String dateNaissance, String historiqueM, String biographie) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.historiqueMedaille = historiqueM;
        this.biographie = biographie;
        System.out.println("Les informations de l'athlète ont été modifiées.");
    }

    // Les méthodes getters pour obtenir les informations de l'athlète.

    /**
     * Méthode pour obtenir l'identifiant de l'athlète.
     *
     * @return L'identifiant de l'athlète.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir le prénom de l'athlète.
     *
     * @return Le prénom de l'athlète.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Méthode pour obtenir le nom de l'athlète.
     *
     * @return Le nom de l'athlète.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la date de naissance de l'athlète.
     *
     * @return La date de naissance de l'athlète.
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

}