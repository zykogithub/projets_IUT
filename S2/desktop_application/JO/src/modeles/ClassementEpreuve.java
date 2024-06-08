package modeles;

/**
 * La classe ClassementEpreuve représente le classement d'un athlète ou d'une équipe dans une épreuve donnée.
 * Elle étend la classe abstraite Classement et inclut des informations sur l'épreuve, l'athlète ou l'équipe associés à ce classement.
 *
 * @author Yousra HACHMI
 */
public class ClassementEpreuve extends Classement  {

    private Epreuve sonEpreuve; // L'épreuve associée à ce classement
    private Athlete sonAthlete; // L'athlète associé à ce classement
    private Equipe sonEquipe; // L'équipe associée à ce classement

    /**
     * Constructeur pour créer un nouveau classement d'épreuve.
     *
     * @param rang Le rang dans le classement.
     * @param score Le score associé au classement.
     * @param sonEpreuve L'épreuve associée à ce classement.
     * @param sonAthlete L'athlète associé à ce classement.
     * @param sonEquipe L'équipe associée à ce classement.
     */
    public ClassementEpreuve(int rang, double score, Epreuve sonEpreuve, Athlete sonAthlete, Equipe sonEquipe) {
        super(rang, score);
        this.sonEpreuve = sonEpreuve;
        this.sonAthlete = sonAthlete;
        this.sonEquipe = sonEquipe;
    }

    // Getters et setters pour les attributs de la classe ClassementEpreuve

    /**
     * Méthode pour obtenir l'épreuve associée à ce classement.
     *
     * @return L'épreuve associée à ce classement.
     */
    public Epreuve getSonEpreuve() {
        return sonEpreuve;
    }

    /**
     * Méthode pour définir l'épreuve associée à ce classement.
     *
     * @param sonEpreuve La nouvelle épreuve associée à ce classement.
     */
    public void setSonEpreuve(Epreuve sonEpreuve) {
        this.sonEpreuve = sonEpreuve;
    }

    /**
     * Méthode pour obtenir l'athlète associé à ce classement.
     *
     * @return L'athlète associé à ce classement.
     */
    public Athlete getSonAthlete() {
        return sonAthlete;
    }

    /**
     * Méthode pour définir l'athlète associé à ce classement.
     *
     * @param sonAthlete Le nouvel athlète associé à ce classement.
     */
    public void setSonAthlete(Athlete sonAthlete) {
        this.sonAthlete = sonAthlete;
    }

    /**
     * Méthode pour obtenir l'équipe associée à ce classement.
     *
     * @return L'équipe associée à ce classement.
     */
    public Equipe getSonEquipe() {
        return sonEquipe;
    }

    /**
     * Méthode pour définir l'équipe associée à ce classement.
     *
     * @param sonEquipe La nouvelle équipe associée à ce classement.
     */
    public void setSonEquipe(Equipe sonEquipe) {
        this.sonEquipe = sonEquipe;
    }
}
