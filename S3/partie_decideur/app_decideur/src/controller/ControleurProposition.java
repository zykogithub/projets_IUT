package controller;

import java.util.ArrayList;

import model.Groupe;
import model.Proposition;
import view.VuePropositions;

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
    public void trierMaxProportionThematique() {
        propositions = groupe.maxProportionThematique(propositions, 0);
    }
    public void trierOptimiserBudgetEtSatisfaction() {
        propositions = groupe.optimiserBudgetEtSatisfaction();
    }
    public ArrayList<Proposition> getPropositions() {
        return propositions;
    }
}
