package modele;

import java.sql.Date;

public class PourContre extends Proposition {
    private float indiceSatisfaction;

    public PourContre(int id, String titre, String description, Date date_publication, float budget, int id_thematique) {
        super(id, titre, description, date_publication, budget, id_thematique);
        this.indiceSatisfaction = indiceSatisfaction();
    }

    public float getIndiceSatisfaction() {
        return indiceSatisfaction;
    }

    public float indiceSatisfaction() {
        float pour = getVotes().getOrDefault("pour", 0);
        float contre = getVotes().getOrDefault("contre", 0);
        float total = pour + contre + getVotes().getOrDefault("abstention", 0);
        float score = pour / total;
        return score;
    }
    
    
    public float getIndiceSucces(int ratioBudget, int ratioSucces, int ratioVotants, float budgetMax) {
        float scoreBudget = 0, scoreSucces = 0, scoreVotants = 0;

        // Calcul scoreVotants
        float pour = getVotes().getOrDefault("pour", 0);
        float contre = getVotes().getOrDefault("contre", 0);
        float abstention = getVotes().getOrDefault("abstention", 0);
        float votants = pour + contre;
        float total = pour + contre + abstention;

        if (total > 0) {
            scoreVotants = (votants / total) * ratioVotants;
        }

        // Calcul scoreSucces
        if (votants > 0) {
            scoreSucces = (pour / votants) * ratioSucces;
        }

        // Calcul scoreBudget (inversion pour donner plus de score aux propositions peu coÃ»teuses)
        if (budgetMax > 0) {
            scoreBudget = ((budgetMax - getBudget()) / budgetMax) * ratioBudget;
        }

        // Retour du score total
        return scoreVotants + scoreSucces + scoreBudget;
    }
}