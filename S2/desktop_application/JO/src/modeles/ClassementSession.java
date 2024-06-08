package modeles;

/**
 * La classe ClassementSession représente le classement d'un athlète ou d'une équipe dans une session donnée.
 * Elle étend la classe abstraite Classement et inclut des informations sur la session, l'athlète ou l'équipe associés à ce classement.
 *
 * @author Yousra HACHMI
 */
public class ClassementSession extends Classement {

    private Athlete sonAthlete; // L'athlète associé à ce classement
    private Session saSession; // La session associée à ce classement
    private Equipe sonEquipe; // L'équipe associée à ce classement

    /**
     * Constructeur pour créer un nouveau classement de session.
     *
     * @param rangSession Le rang dans le classement de session.
     * @param scoreSession Le score associé au classement de session.
     * @param sonAthlete L'athlète associé à ce classement de session.
     * @param saSession La session associée à ce classement de session.
     * @param sonEquipe L'équipe associée à ce classement de session.
     */
    public ClassementSession(int rangSession, double scoreSession, Athlete sonAthlete, Session saSession, Equipe sonEquipe) {
        super(rangSession, scoreSession);
        this.sonAthlete = sonAthlete;
        this.saSession = saSession;
        this.sonEquipe = sonEquipe;
    }

    // Getters et setters pour les attributs de la classe ClassementSession

    /**
     * Méthode pour obtenir l'athlète associé à ce classement de session.
     *
     * @return L'athlète associé à ce classement de session.
     */
    public Athlete getSonAthlete() {
        return sonAthlete;
    }

    /**
     * Méthode pour définir l'athlète associé à ce classement de session.
     *
     * @param sonAthlete Le nouvel athlète associé à ce classement de session.
     */
    public void setSonAthlete(Athlete sonAthlete) {
        this.sonAthlete = sonAthlete;
    }

    /**
     * Méthode pour obtenir la session associée à ce classement de session.
     *
     * @return La session associée à ce classement de session.
     */
    public Session getSaSession() {
        return saSession;
    }

    /**
     * Méthode pour définir la session associée à ce classement de session.
     *
     * @param saSession La nouvelle session associée à ce classement de session.
     */
    public void setSaSession(Session saSession) {
        this.saSession = saSession;
    }

    /**
     * Méthode pour obtenir l'équipe associée à ce classement de session.
     *
     * @return L'équipe associée à ce classement de session.
     */
    public Equipe getSonEquipe() {
        return sonEquipe;
    }

    /**
     * Méthode pour définir l'équipe associée à ce classement de session.
     *
     * @param sonEquipe La nouvelle équipe associée à ce classement de session.
     */
    public void setSonEquipe(Equipe sonEquipe) {
        this.sonEquipe = sonEquipe;
    }
}
