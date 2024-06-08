package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente une session d'événements pendant les Jeux Olympiques de 2024.
 * Elle contient des informations sur la session telles que la date, l'horaire, les classements, les athlètes et les équipes participant à la session.
 * Une session peut avoir plusieurs classements, athlètes et équipes.
 * 
 * @author Yousra HACHMI
 */
public class Session implements Serializable {

    ArrayList<ClassementSession> sesClassements;
    Planning sonPlanning;
    Epreuve sonEpreuve;
    ArrayList<Athlete> sesAthletes;
    ArrayList<Equipe> sesEquipes;
    private int identifiant;
    private String dateSession;
    private String horaireSession;

    /**
     * Constructeur pour créer une session avec les informations initiales.
     *
     * @param id               L'identifiant de la session.
     * @param dateSession      La date de la session.
     * @param horaireSession   L'horaire de la session.
     */
    public Session(int id, String dateSession, String horaireSession) {
        this.identifiant = id;
        this.dateSession = dateSession;
        this.horaireSession = horaireSession;
        this.sesClassements = new ArrayList<>();
        this.sesAthletes = new ArrayList<>();
        this.sesEquipes = new ArrayList<>();
    }

    /**
     * Méthode pour modifier une session avec une nouvelle date et un nouvel horaire.
     *
     * @param dateSession    La nouvelle date de la session.
     * @param horaires       Le nouvel horaire de la session.
     */
    public void modifierSession(String dateSession, String horaires) {
        this.dateSession = dateSession;
        this.horaireSession = horaires;
    }

    /**
     * Méthode pour ajouter un classement à la session.
     *
     * @param classement Le classement à ajouter.
     */
    public void ajouterClassement(ClassementSession classement) {
        sesClassements.add(classement);
        System.out.println("Classement ajouté à la session.");
    }

    /**
     * Méthode pour supprimer un classement de la session par identifiant de l'athlète.
     *
     * @param idAthlete L'identifiant de l'athlète du classement à supprimer.
     */
    public void supprimerClassement(int idAthlete) {
        for (int i = 0; i < sesClassements.size(); i++) {
            if (sesClassements.get(i).getSonAthlete().getIdentifiant() == idAthlete) {
                sesClassements.remove(i);
                System.out.println("Classement de l'athlète avec l'identifiant " + idAthlete + " a été supprimé.");
                return;
            }
        }
        System.out.println("Aucun classement trouvé pour l'athlète avec l'identifiant " + idAthlete + ".");
    }

    /**
     * Méthode pour ajouter un athlète à la session.
     *
     * @param athlete L'athlète à ajouter.
     */
    public void ajouterAthlete(Athlete athlete) {
        sesAthletes.add(athlete);
        System.out.println("L'athlète " + athlete.getNom() + " a été ajouté à la session " + this.dateSession);
    }

    /**
     * Méthode pour supprimer un athlète de la session par identifiant.
     *
     * @param idAthlete L'identifiant de l'athlète à supprimer.
     */
    public void supprimerAthlete(int idAthlete) {
        for (int i = 0; i < sesAthletes.size(); i++) {
            if (sesAthletes.get(i).getIdentifiant() == idAthlete) {
                System.out.println("L'athlète " + sesAthletes.get(i).getNom() + " a été supprimé de la session " + this.dateSession);
                sesAthletes.remove(i);
                return;
            }
        }
        System.out.println("Aucun athlète trouvé avec l'identifiant " + idAthlete + " dans la session " + this.dateSession);
    }

    /**
     * Méthode pour ajouter une équipe à la session.
     *
     * @param equipe L'équipe à ajouter.
     */
    public void ajouterEquipe(Equipe equipe) {
        sesEquipes.add(equipe);
        System.out.println("L'équipe " + equipe.getNom() + " a été ajoutée à la session " + this.dateSession);
    }

    /**
     * Méthode pour supprimer une équipe de la session par identifiant.
     *
     * @param idEquipe L'identifiant de l'équipe à supprimer.
     */
    public void supprimerEquipe(int idEquipe) {
        for (int i = 0; i < sesEquipes.size(); i++) {
            if (sesEquipes.get(i).getIdentifiant() == idEquipe) {
                System.out.println("L'équipe " + sesEquipes.get(i).getNom() + " a été supprimée de la session " + this.dateSession);
                sesEquipes.remove(i);
                return;
            }
        }
        System.out.println("Aucune équipe trouvée avec l'identifiant " + idEquipe + " dans la session " + this.dateSession);
    }

    /**
     * Méthode pour obtenir l'identifiant de la session.
     *
     * @return L'identifiant de la session.
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir la date de la session.
     *
     * @return La date de la session.
     */
    public String getDateSession() {
        return dateSession;
    }

    /**
     * Méthode pour obtenir l'horaire de la session.
     *
     * @return L'horaire de la session.
     */
    public String getHoraireSession() {
        return horaireSession;
    }

    /**
     * Méthode pour obtenir la liste des classements de la session.
     *
     * @return La liste des classements de la session.
     */
    public ArrayList<ClassementSession> getSesClassements() {
        return sesClassements;
    }

    /**
     * Méthode pour obtenir la liste des athlètes de la session.
     *
     * @return La liste des athlètes de la session.
     */
    public ArrayList<Athlete> getSesAthletes() {
        return sesAthletes;
    }

    /**
     * Méthode pour obtenir la liste des équipes de la session.
     *
     * @return La liste des équipes de la session.
     */
    public ArrayList<Equipe> getSesEquipes() {
        return sesEquipes;
    }
    
    public Epreuve getEpreuve()
    {
    	
    	return sonEpreuve;
    }
    public void setEpreuve(Epreuve epreuve) {
    	sonEpreuve = epreuve;
    }
}

