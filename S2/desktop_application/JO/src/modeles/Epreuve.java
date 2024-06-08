package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente une épreuve des Jeux Olympiques de 2024.
 * Elle contient des informations sur l'épreuve telles que le nom, la date de début et la date de fin,
 * ainsi que les sessions, les athlètes, les équipes et les classements associés.
 * 
 * @author Yousra HACHMI
 */
public class Epreuve implements Serializable {
    
    Discipline saDiscipline;
    ArrayList<Session> sesSessions;
    ArrayList<Athlete> sesAthletes;
    ArrayList<Equipe> sesEquipes;
    ArrayList<ClassementEpreuve> sesClassements;
    private int identifiant;
    private String nom;
    private String dateDebut;
    private String dateFin;
    private int classement;

    /**
     * Constructeur pour créer une épreuve avec les informations initiales.
     *
     * @param id         L'identifiant de l'épreuve.
     * @param nom        Le nom de l'épreuve.
     * @param dateDebut  La date de début de l'épreuve.
     * @param dateFin    La date de fin de l'épreuve.
     */
    public Epreuve(int id, String nom, String dateDebut, String dateFin) {
        this.identifiant = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sesSessions = new ArrayList<>();
        this.sesAthletes = new ArrayList<>();
        this.sesEquipes = new ArrayList<>();
        this.sesClassements = new ArrayList<>();
        this.classement = 0; // Initialisation du classement à 0 par défaut
    }

    /**
     * Méthode pour modifier les détails de l'épreuve avec un nouveau nom, une nouvelle date de début et une nouvelle date de fin.
     *
     * @param nom        Le nouveau nom de l'épreuve.
     * @param dateDebut  La nouvelle date de début de l'épreuve.
     * @param dateFin    La nouvelle date de fin de l'épreuve.
     */
    public void modifierEpreuve(String nom, String dateDebut, String dateFin) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    /**
     * Méthode pour supprimer l'épreuve en réinitialisant ses attributs.
     */
    public void supprimerEpreuve() {
        this.identifiant = 0;
        this.nom = null;
        this.dateDebut = null;
        this.dateFin = null;
        this.sesSessions.clear();
        this.sesAthletes.clear();
        this.sesEquipes.clear();
        this.sesClassements.clear();
        this.classement = 0;
    }

    /**
     * Méthode pour ajouter un classement à l'épreuve.
     *
     * @param classement Le classement à ajouter.
     */
    public void ajouterClassement(ClassementEpreuve classement) {
        sesClassements.add(classement);
    }

    /**
     * Méthode pour supprimer un classement de l'épreuve par identifiant de l'athlète.
     *
     * @param idAthlete L'identifiant de l'athlète du classement à supprimer.
     */
    public void supprimerClassement(int idAthlete) {
        for (int i = 0; i < sesClassements.size(); i++) {
            if (sesClassements.get(i).getSonAthlete().getIdentifiant() == idAthlete) {
                System.out.println("Le classement de l'athlète avec l'identifiant " + idAthlete + " a été supprimé de l'épreuve " + this.nom);
                sesClassements.remove(i);
                return;
            }
        }
        System.out.println("Aucun classement trouvé pour l'athlète avec l'identifiant " + idAthlete + " dans l'épreuve " + this.nom);
    }

    /**
     * Méthode pour ajouter une session à l'épreuve.
     *
     * @param session La session à ajouter.
     */
    public void ajouterSession(Session session) {
        sesSessions.add(session);
    }

    /**
     * Méthode pour supprimer une session de l'épreuve par identifiant.
     *
     * @param idSession L'identifiant de la session à supprimer.
     */
    public void supprimerSession(int idSession) {
        for (int i = 0; i < sesSessions.size(); i++) {
            if (sesSessions.get(i).getIdentifiant() == idSession) {
                sesSessions.remove(i);
                System.out.println("La session avec l'identifiant " + idSession + " a été supprimée.");
                return;
            }
        }
        System.out.println("Aucune session trouvée avec l'identifiant " + idSession);
    }

    /**
     * Méthode pour ajouter une équipe à l'épreuve.
     *
     * @param equipe L'équipe à ajouter.
     */
    public void ajouterEquipe(Equipe equipe) {
        sesEquipes.add(equipe);
    }

    /**
     * Méthode pour supprimer une équipe de l'épreuve par identifiant.
     *
     * @param idEquipe L'identifiant de l'équipe à supprimer.
     */
    public void supprimerEquipe(int idEquipe) {
        for (int i = 0; i < sesEquipes.size(); i++) {
            if (sesEquipes.get(i).getIdentifiant() == idEquipe) {
                sesEquipes.remove(i);
                System.out.println("L'équipe avec l'identifiant " + idEquipe + " a été supprimée.");
                return;
            }
        }
        System.out.println("Aucune équipe trouvée avec l'identifiant " + idEquipe);
    }

    /**
     * Méthode pour ajouter un athlète à l'épreuve.
     *
     * @param athlete L'athlète à ajouter.
     */
    public void ajouterAthlete(Athlete athlete) {
        sesAthletes.add(athlete);
    }

    /**
     * Méthode pour supprimer un athlète de l'épreuve par identifiant.
     *
     * @param idAthlete L'identifiant de l'athlète à supprimer.
     */
    
    public void supprimerAthlete(int idAthlete) {
        for (int i = 0; i < sesAthletes.size(); i++) {
            if (sesAthletes.get(i).getIdentifiant() == idAthlete) {
                sesAthletes.remove(i);
                System.out.println("L'athlète avec l'identifiant " + idAthlete + " a été supprimé.");
                return;
            }
        }
        System.out.println("Aucun athlète trouvé avec l'identifiant " + idAthlete);
    }

    /**
     * Méthode pour obtenir l'identifiant de l'épreuve.
     *
     * @return L'identifiant de l'épreuve.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir le nom de l'épreuve.
     *
     * @return Le nom de l'épreuve.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir la date de début de l'épreuve.
     *
     * @return La date de début de l'épreuve.
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Méthode pour obtenir la date de fin de l'épreuve.
     *
     * @return La date de fin de l'épreuve.
     */
    public String getDateFin() {
        return dateFin;
    }

    /**
     * Méthode pour obtenir la liste des sessions de l'épreuve.
     *
     * @return La liste des sessions de l'épreuve.
     */
    public ArrayList<Session> getSesSessions() {
        return sesSessions;
    }

    /**
     * Méthode pour obtenir la liste des athlètes de l'épreuve.
     *
     * @return La liste des athlètes de l'épreuve.
     */
    public ArrayList<Athlete> getSesAthletes() {
        return sesAthletes;
    }

    /**
     * Méthode pour obtenir la liste des équipes de l'épreuve.
     *
     * @return La liste des équipes de l'épreuve.
     */
    public ArrayList<Equipe> getSesEquipes() {
        return sesEquipes;
    }

    /**
     * Méthode pour obtenir le classement de l'épreuve.
     *
     * @return Le classement de l'épreuve.
     */
    public int getClassement() {
        return classement;
    }

    /**
     * Méthode pour définir le classement de l'épreuve.
     *
     * @param classement Le classement à définir.
     */
    public void setClassement(int classement) {
        this.classement = classement;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    
}
























