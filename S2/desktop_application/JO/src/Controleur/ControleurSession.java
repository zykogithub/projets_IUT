package Controleur;

import modeles.Epreuve;
import modeles.Planning;
import modeles.Session;
import vue.VueCreationSession;
import vue.VuePlanning;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Le contrôleur pour gérer les actions liées à la création de sessions dans les Jeux Olympiques.
 * Ce contrôleur écoute les événements provenant de la vue de création de session et interagit avec le modèle (Planning) et la vue principale (VuePlanning).
 * Il est responsable d'ajouter une nouvelle session au planning et de la faire afficher dans la vue principale.
 * 
 * @author Lyane Legernard
 */
public class ControleurSession implements ActionListener {
    private Planning planning; // Le modèle de données contenant les sessions
    private VueCreationSession vueCreationSession; // La vue de création de session
    private VuePlanning vuePlanning; // La vue principale affichant les sessions
    private JFrame fenetre; // La fenêtre principale de l'application

    /**
     * Constructeur pour créer une instance de ContrôleurSession.
     * 
     * @param vueCreationSession La vue de création de session à gérer.
     * @param planning Le modèle de données (Planning) des sessions.
     * @param vuePlanning La vue principale (VuePlanning) à mettre à jour.
     * @param frame La fenêtre principale de l'application.
     */
    public ControleurSession(VueCreationSession vueCreationSession, Planning planning, VuePlanning vuePlanning, JFrame frame) {
        this.planning = planning;
        this.vueCreationSession = vueCreationSession;
        this.vuePlanning = vuePlanning;
        this.fenetre = frame;
    }

    /**
     * Méthode pour gérer les actions déclenchées par les composants de la vue de création de session.
     * 
     * @param e L'événement d'action à traiter.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) { // Vérifie si la source de l'événement est un bouton
            JButton boutton = (JButton) e.getSource();
            if ("Ajouter".equals(e.getActionCommand())) { // Vérifie si l'action command est "Ajouter"
                // Vérifie si tous les champs nécessaires sont remplis
                if (vueCreationSession.getNomField().getText() != null &&
                    vueCreationSession.getIdField().getText() != null &&
                    vueCreationSession.getDateField().getText() != null &&
                    vueCreationSession.getEpreuveField().getText() != null &&
                    vueCreationSession.getHoraireField().getText() != null) {
                    try {
                        // Rien n'est fait dans ce bloc try pour l'instant

                    } catch (NumberFormatException er) {
                        JOptionPane.showMessageDialog(null, "Les identifiants sont mal écrits", "erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // Validation du format de la date
                    Pattern pattern = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");
                    Matcher matcher = pattern.matcher(vueCreationSession.getDateField().getText());
                    boolean matchFound = matcher.find();

                    if (!matchFound) { // Si le format de la date n'est pas correct
                        JOptionPane.showMessageDialog(null, "La date n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // Validation du format de l'horaire
                    pattern = Pattern.compile("^(\\d{2}):(\\d{2})");
                    matcher = pattern.matcher(vueCreationSession.getHoraireField().getText());
                    if (!matcher.find()) { // Si le format de l'horaire n'est pas correct
                        JOptionPane.showMessageDialog(null, "L'horaire n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // Création de la session et de l'épreuve associée
                    Session modeleession = new Session(Integer.parseInt(vueCreationSession.getIdField().getText()), vueCreationSession.getDateField().getText(), vueCreationSession.getHoraireField().getText());
                    Epreuve modelEpreuve = new Epreuve(Integer.parseInt(vueCreationSession.getIdField().getText()), vueCreationSession.getNomField().getText(), vueCreationSession.getDateField().getText(), "null");
                    modeleession.setEpreuve(modelEpreuve);

                    // Ajout de la session au planning et à la vue
                    vuePlanning.ajouterSession(modeleession);

                    // Fermeture de la fenêtre de création et affichage d'un message de confirmation
                    fenetre.dispose();
                    JOptionPane.showConfirmDialog(null, "Session créée", "confirmation", JOptionPane.CLOSED_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "Il manque des champs à compléter", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
