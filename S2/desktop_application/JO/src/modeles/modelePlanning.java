package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe représentant un planning des sessions.
 * Cette classe gère les sessions et leur nombre.
 * 
 * @author Yousra HACHMI
 */
public class modelePlanning implements Serializable {
    private ArrayList<Session> sesSessions;
    private int nbSessions;

    /**
     * Constructeur pour initialiser le modèle de planning.
     */
    public modelePlanning() {
        sesSessions = new ArrayList<>();
        nbSessions = 0;
    }

    /**
     * Obtient la liste des sessions.
     * 
     * @return La liste des sessions.
     */
    public ArrayList<Session> getSesSessions() {
        return sesSessions;
    }

    /**
     * Définit la liste des sessions.
     * 
     * @param sesSessions La nouvelle liste des sessions.
     */
    public void setSesSessions(ArrayList<Session> sesSessions) {
        this.sesSessions = sesSessions;
    }

    /**
     * Obtient le nombre de sessions.
     * 
     * @return Le nombre de sessions.
     */
    public int getNbSessions() {
        return nbSessions;
    }

    /**
     * Définit le nombre de sessions.
     * 
     * @param nbSessions Le nouveau nombre de sessions.
     */
    public void setNbSessions(int nbSessions) {
        this.nbSessions = nbSessions;
    }

    /**
     * Méthode pour supprimer une session.
     * 
     * À implémenter.
     */
    public void supprimerSession() {
        // Implémentation à venir
    }
}
