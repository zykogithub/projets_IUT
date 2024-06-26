package modeles;

import java.io.Serializable;
import java.util.ArrayList;

import javax.management.ValueExp;

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
        sesSessions = new ArrayList<Session>();
    }
    public Planning(ArrayList<Session> sessions) {
        nbSessions = 0;
        sesSessions = sessions;
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
    public Epreuve rechercherNomEpreuve(int int1) throws NoSuchFieldError {
        int i = 0;
        // remarque : le mieux aurait été de chercher la donnée dans la collection d'épreuve crée. Le souccis c'est que c'est pas possible
        while (!(sesSessions.get(i).sonEpreuve.getIdentifiant()==int1) && i<sesSessions.size()) {
            i+=1;
        }
    
        if (i==sesSessions.size()) {
            throw new NoSuchFieldError();
            
        }
        else{
            return sesSessions.get(i).sonEpreuve;
        }
        
    }
    
}
