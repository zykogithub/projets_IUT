package modeles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente un lieu où se déroulent des événements des Jeux Olympiques de 2024.
 * Elle contient des informations sur le lieu telles que le nom, l'adresse et les disciplines qui auront lieu là-bas.
 * Un lieu peut accueillir plusieurs disciplines.
 * 
 * @author Yousra HACHMI
 */
public class Lieu implements Serializable {

	// Les disciplines qui auront lieu dans le lieu
    ArrayList<Discipline> sesDisciplines;
    private int identifiant;
    private String nom;
    private String adresse;

    /**
     * Constructeur pour créer un lieu avec les informations initiales.
     *
     * @param id        L'identifiant du lieu.
     * @param nom       Le nom du lieu.
     * @param adresse   L'adresse du lieu.
     */
    public Lieu(int id, String nom, String adresse) {
        this.identifiant = id;
        this.nom = nom;
        this.adresse = adresse;
        this.sesDisciplines = new ArrayList<>();
    }

    /**
     * Méthode pour ajouter une discipline au lieu.
     *
     * @param discipline La discipline à ajouter.
     */
    public void ajouterDisciplineLieu(Discipline discipline) {
        if (!sesDisciplines.contains(discipline)) {
            sesDisciplines.add(discipline);
        } else {
            System.out.println("La discipline existe déjà.");
        }
    }

    /**
     * Méthode pour supprimer une discipline du lieu.
     *
     * @param discipline La discipline à supprimer.
     */
    public void supprimerDisciplineLieu(Discipline discipline) {
        if (sesDisciplines.contains(discipline)) {
            sesDisciplines.remove(discipline);
        } else {
            System.out.println("La discipline à supprimer n'existe pas.");
        }
    }

    /**
     * Méthode pour obtenir l'identifiant du lieu.
     *
     * @return L'identifiant du lieu.
     */
    public int getIdentifiantLieu() {
        return identifiant;
    }

    /**
     * Méthode pour obtenir le nom du lieu.
     *
     * @return Le nom du lieu.
     */
    public String getNomLieu() {
        return nom;
    }

    /**
     * Méthode pour obtenir l'adresse du lieu.
     *
     * @return L'adresse du lieu.
     */
    public String getAdresseLieu() {
        return adresse;
    }

    /**
     * Méthode pour obtenir la liste des disciplines ayant lieu dans ce lieu.
     *
     * @return La liste des disciplines du lieu.
     */
    public ArrayList<Discipline> getSesDisciplines() {
        return sesDisciplines;
    }
}
