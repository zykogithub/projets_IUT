package modeles;

import java.io.Serializable;

/**
 * La classe abstraite Classement représente un classement quelconque.
 * Elle contient des informations sur le rang et le score associés à ce classement.
 *
 * @author Yousra HACHMI
 */
public abstract class Classement implements Serializable {

    private int rang; // Le rang dans le classement
    private double score; // Le score associé au classement

    /**
     * Constructeur protégé pour les sous-classes.
     *
     * @param rang Le rang dans le classement.
     * @param score Le score associé au classement.
     */
    protected Classement(int rang, double score) {
        this.rang = rang;
        this.score = score;
    }

    /**
     * Méthode pour obtenir le rang dans le classement.
     *
     * @return Le rang dans le classement.
     */
    public int getRang() {
        return rang;
    }

    /**
     * Méthode pour définir le rang dans le classement.
     *
     * @param rang Le nouveau rang dans le classement.
     */
    public void setRang(int rang) {
        this.rang = rang;
    }

    /**
     * Méthode pour obtenir le score associé au classement.
     *
     * @return Le score associé au classement.
     */
    public double getScore() {
        return score;
    }

    /**
     * Méthode pour définir le score associé au classement.
     *
     * @param score Le nouveau score associé au classement.
     */
    public void setScore(double score) {
        this.score = score;
    }
}
