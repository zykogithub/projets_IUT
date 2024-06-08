package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Planning représente le planning de sessions.
 * Elle contient une liste de sessions et le nombre de sessions.
 * 
 * @autor Lyane Legernard et Ambre VIAU
 */
public class Planning implements Serializable {
    // Attributs de la classe Planning

    private ArrayList<Session> sesSessions; // Liste des sessions du planning
    private int nbSessions; // Nombre de sessions dans le planning

    /**
     * Constructeur par défaut pour créer un planning vide.
     */
    public Planning() {
        nbSessions = 0;
    }

    /**
     * Méthode pour obtenir la liste des sessions du planning.
     *
     * @return La liste des sessions.
     */
    public ArrayList<Session> getSesSessions() {
        return sesSessions;
    }

    /**
     * Méthode pour définir la liste des sessions du planning.
     *
     * @param sesSessions La nouvelle liste de sessions.
     */
    public void setSesSessions(ArrayList<Session> sesSessions) {
        this.sesSessions = sesSessions;
    }

    /**
     * Méthode pour obtenir le nombre de sessions dans le planning.
     *
     * @return Le nombre de sessions.
     */
    public int getNbSessions() {
        return nbSessions;
    }

    /**
     * Méthode pour définir le nombre de sessions dans le planning.
     *
     * @param nbSessions Le nouveau nombre de sessions.
     */
    public void setNbSessions(int nbSessions) {
        this.nbSessions = nbSessions;
    }

    /**
     * Méthode pour supprimer une session du planning.
     */
    public void supprimerSession() {
        // Implémentation de la méthode pour supprimer une session
    }
}
