package modele;

public class Thematique {
	private int id;
	private String nom;
	private float budget;

	Thematique(int id, String nom, float budget){
		this.id=id;
		this.nom=nom;
		this.budget=budget;
	}

    public String toString() {
        return id + " ; " + nom + " ; " + budget;
    }
	
	public int getId() {return id;}
	public String getNom() {return nom;}
	public float getBudget() {return budget;}
}
