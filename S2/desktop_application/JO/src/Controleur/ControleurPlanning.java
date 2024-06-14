
/**
 * Le contrôleur pour gérer les actions dans la vue du planning des Jeux Olympiques.
 * Ce contrôleur écoute les événements provenant de la vue, tels que l'ajout ou la suppression de sessions.
 * Il interagit avec le modèle de données (Planning) et la vue (VuePlanning).
 * 
 * @author Naherry MOHAMED DAROUECHE
 */
package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modeles.*;
import vue.*;

public class ControleurPlanning implements ActionListener {
    private Planning model;
    private VuePlanning vue;
    private VueCreationSession vueCreationSession;
    JFrame fenetre;

    /**
     * Constructeur pour créer une instance de ContrôleurPlanning.
     * 
     * @param p Le modèle de données (Planning) à gérer.
     * @param v La vue associée (VuePlanning) à écouter.
     * @param vueCreationSession La vue de création de session.
     * @param frame La fenêtre principale.
     */
    public ControleurPlanning(Planning p, VuePlanning v, VueCreationSession vueCreationSession, JFrame frame) {
        model = p;
        vue = v;
        this.vueCreationSession = vueCreationSession;
        this.fenetre = frame;
    }

    public ControleurPlanning(Planning p, VuePlanning v, VueCreationSession vueCreationSession) {
        model = p;
        vue = v;
        this.vueCreationSession = vueCreationSession;
        this.fenetre = null;
    }

    /**
     * Méthode pour gérer les actions déclenchées par les composants de la vue.
     * 
     * @param e L'événement d'action à traiter.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == VuePlanning.ACTION_AJOUTER) 
        {
            JOptionPane.showMessageDialog(null, "La date est au format aaaa-mm-jj et l'heure est au format hh:mm et id=identifiant, un nombre de 5 chifre","information",JOptionPane.INFORMATION_MESSAGE);
            afficher();
        } else if (e.getActionCommand() == VuePlanning.ACTION_SUPPRIMER) {
            vue.supprimerSessionSelectionnee();
        } 
        else if (e.getActionCommand() == VueCreationSession.ACTION_AJOUTER) 
        {   
            if ((vueCreationSession.getIdField().getText() != null && !vueCreationSession.getNomField().getText().equals("")) && (vueCreationSession.getEpreuveField().getText() != null &&!vueCreationSession.getEpreuveField().getText().equals("")) && (vueCreationSession.getDateField().getText() != null &&!vueCreationSession.getDateField().getText().equals("")) && (vueCreationSession.getEpreuveField().getText() != null &&!vueCreationSession.getEpreuveField().getText().equals("")) && (vueCreationSession.getHoraireField().getText() != null &&!vueCreationSession.getHoraireField().getText().equals(""))) {
                System.out.println(vueCreationSession.getIdField().getText()+" cdqca");
                Pattern pattern = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");
                Matcher matcher = pattern.matcher(vueCreationSession.getDateField().getText());
                boolean matchFound = matcher.find();

                if (!matchFound) {
                    JOptionPane.showMessageDialog(null, "La date n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                }
                pattern = Pattern.compile("^(\\d{2}):(\\d{2})$");
                matcher = pattern.matcher(vueCreationSession.getHoraireField().getText());
                matchFound = matcher.find();
                if (!matchFound) {
                    JOptionPane.showMessageDialog(null, "L'horaire n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    Session modeleession = new Session(Integer.parseInt(vueCreationSession.getIdField().getText()), vueCreationSession.getDateField().getText(), vueCreationSession.getHoraireField().getText());
                    Epreuve modelEpreuve = new Epreuve(Integer.parseInt(vueCreationSession.getIdField().getText()), vueCreationSession.getNomField().getText(), vueCreationSession.getDateField().getText(), "null");
                    modeleession.setEpreuve(modelEpreuve);
                    vue.ajouterSession(modeleession);
                    this.vue.getPlanning().getSesSessions().add(modeleession);
                    fenetre.dispose();
                    JOptionPane.showConfirmDialog(null, "Session créée", "confirmation", JOptionPane.CLOSED_OPTION);
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(null, "Les identifiants sont mal écrits", "erreur", JOptionPane.ERROR_MESSAGE);
                }

            } 
            else {
                JOptionPane.showMessageDialog(null, "Il manque des champs à compléter", "erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Méthode pour afficher la vue de création de session des Jeux Olympiques.
     */
    public void afficher() {

        JFrame fenetre = new JFrame("Création session des Jeux Olympiques");
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        VueCreationSession creationPanel = new VueCreationSession();
        creationPanel.getAjouterButton().addActionListener(this);
        fenetre.setSize(400, 300);
        fenetre.add(creationPanel);
        fenetre.setVisible(true);
    }
}
