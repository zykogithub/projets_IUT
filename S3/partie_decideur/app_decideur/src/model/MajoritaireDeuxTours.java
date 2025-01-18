package model;
import java.sql.Date;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author tous le groupe
 */
public class MajoritaireDeuxTours extends Proposition {
    float indiceSatisfaction;

    public MajoritaireDeuxTours(int id, String titre, String description, Date date_publication, float budget, int id_thematique) {
        super(id, titre, description, date_publication, budget, id_thematique);
        this.indiceSatisfaction = indiceSatisfaction();
    }

    public float getIndiceSatisfaction() {
        return indiceSatisfaction;
    }

    public float indiceSatisfaction() {
        int nbChoix = getVotes().size();
        if (nbChoix <= 1) {
            return 0;
        }
    
        int max = 0;
        int total = 0;
        int i=0;
        
        for (Entry<Integer, Integer> entry : getVotes().entrySet()) {
            int nbVotes=entry.getValue();
        	total+=nbVotes;
            i+=1;
            
            if ((i<nbChoix)&&(nbVotes > max)) {
                max = nbVotes;
            }
        }

        if (total == 0) {
            return 0;
        }
    
        return (float) max / total;
    }

    
    public float getIndiceSucces(int ratioBudget, int ratioSucces, int ratioVotants, float budgetMax) {
        float scoreBudget = 0, scoreSucces = 0, scoreVotants = 0;

        // Calcul scoreVotants : proportion des votants sur les membres totaux
        int totalVotes = getVotes().size();
        int maxChoixVotes = 0;
        int totalChoixVotes = 0;

        for (Map.Entry<Integer, Integer> entry : getVotes().entrySet()) {
            int votes = entry.getValue();
            totalChoixVotes += votes;
            if (votes > maxChoixVotes) {
                maxChoixVotes = votes;
            }
        }

        // Proportion de participation
        if (totalVotes > 0) {
            scoreVotants = ((float) totalVotes / totalChoixVotes) * ratioVotants;
        }

        // Calcul scoreSucces : proportion du choix majoritaire par rapport au total des votes
        if (totalChoixVotes > 0) {
            scoreSucces = ((float) maxChoixVotes / totalChoixVotes) * ratioSucces;
        }

        // Calcul scoreBudget (inversion pour privilégier les propositions économiques)
        if (budgetMax > 0) {
            scoreBudget = ((budgetMax - getBudget()) / budgetMax) * ratioBudget;
        }

        // Retour du score total
        return scoreVotants + scoreSucces + scoreBudget;
    }
}
