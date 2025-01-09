package controleur;

import java.util.ArrayList;
import modele.Groupe;
import modele.Proposition;
import vue.VuePropositions;

public class ControleurProposition {
    private VuePropositions vue;
    private ArrayList<Proposition> propositions;
    private Groupe groupe;
    public ControleurProposition(VuePropositions vue, Groupe groupe) {
        this.vue = vue;
        this.groupe = groupe;
        this.propositions = this.groupe.getPropositions();
    }
    public void trierPlusDePropositionsParthematique() {
        propositions = groupe.lePlusDePropositionsParThematique();
    }
    public void trierPropositionsMaxSatisfaction() {
        propositions = groupe.maxProportionThematique(propositions, 0);
    }
    public void trierPropositionsParBudgetEtSatisfaction() {
        propositions = groupe.optimiserBudgetEtSatisfaction();
    }
    public ArrayList<Proposition> getPropositions() {
        return propositions;
    }
    

    


}
