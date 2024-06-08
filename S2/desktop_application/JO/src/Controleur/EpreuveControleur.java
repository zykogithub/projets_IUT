package Controleur;

import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import vue.VueEpreuve;
import modeles.Epreuve;

/**
 * Classe pour contrôleur des équipes pour toute l'application
 * @author Naherry MOHAMED DAROUECHE
 */
public class EpreuveControleur implements ActionListener {
    private VueEpreuve vue; // La vue associée à ce contrôleur

    /**
     * Constructeur d'un contrôleur avec comme paramètre une vue 
     * @param vue la vue en question
     * @author Naherry MOHAMED DAROUECHE
     */
    public EpreuveControleur(VueEpreuve vue) {
        this.vue = vue;
        // Ajout des listeners pour les boutons de la vue
        vue.getButtonAjout().addActionListener(this);
        vue.getButtonModif().addActionListener(this);
        vue.getButtonSupp().addActionListener(this);
    }

    /**
     * Méthode pour gérer les actions déclenchées par les composants de la vue.
     * @param e L'événement d'action à traiter.
     * @author Naherry MOHAMED DAROUECHE
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) { // Vérifie si la source de l'événement est un bouton
            JButton boutton = (JButton) e.getSource();
            String action = boutton.getText(); // Récupère le texte du bouton pour identifier l'action

            if (action.equals("Supprimer")) { // Action pour supprimer une épreuve
                if (vue.getObjectRow() instanceof Epreuve) {
                    Epreuve epreuve = (Epreuve) vue.getObjectRow();
                    vue.removeRow(epreuve);
                    JOptionPane.showMessageDialog(null, "Colonne supprimée", "validation", JOptionPane.CLOSED_OPTION);
                } else {
                    JOptionPane.showConfirmDialog(null, "Aucune ligne sélectionnée", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            } 
            else if (action.equals("Ajouter")) { // Action pour ajouter une nouvelle épreuve
                String nom = JOptionPane.showInputDialog("Veuillez donnez un nouveau nom d'équipe");
                if (nom == null) {
                    JOptionPane.showMessageDialog(null, "Aucun nom n'a été fourni", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                String identifiant = JOptionPane.showInputDialog("Veuillez donnez un identifiant d'équipe");
                int id = Integer.parseInt(identifiant);
                String dateDebut = JOptionPane.showInputDialog("Veuillez donnez une date de début de création de l'épreuve au format aaaa-mm-jj");
                Pattern pattern = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");
                Matcher matcher = pattern.matcher(dateDebut);
                boolean matchFound = matcher.find();
                
                if (!matchFound) {
                    JOptionPane.showMessageDialog(null, "La date n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                    dateDebut = JOptionPane.showInputDialog("Veuillez donnez une date de début de création de l'épreuve au format aaaa-mm-jj");
                }
                String dateFin = JOptionPane.showInputDialog("Veuillez donnez une date de début de création de l'épreuve au format aaaa-mm-jj");
                if (!matchFound) {
                    JOptionPane.showMessageDialog(null, "La date n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                    dateDebut = JOptionPane.showInputDialog("Veuillez donnez une date de début de création de l'épreuve au format aaaa-mm-jj");
                }
                Epreuve epreuve = new Epreuve(id, nom, dateDebut, dateFin);
                vue.addRow(epreuve);
                JOptionPane.showMessageDialog(null, "Colonne ajoutée", "validation", JOptionPane.CLOSED_OPTION);
            } 
            else if (action.equals("Modifier")) { // Action pour modifier une épreuve existante
                int ligne = vue.getTable().getSelectedRow();
                if (ligne == -1) {
                    JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée", "erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    String nom = JOptionPane.showInputDialog("Veuillez donnez un nouveau nom d'équipe");
                    if (nom == null) {
                        JOptionPane.showMessageDialog(null, "Aucun nom n'a été fourni", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    String identifiant = JOptionPane.showInputDialog("Veuillez donnez un identifiant d'équipe");
                    int id = Integer.parseInt(identifiant);
                    String dateDebut = JOptionPane.showInputDialog("Veuillez donnez une date de début de création de l'épreuve au format aaaa-mm-jj");
                    Pattern pattern = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");
                    Matcher matcher = pattern.matcher(dateDebut);
                    boolean matchFound = matcher.matches();
                    
                    if (!matchFound) {
                        JOptionPane.showMessageDialog(null, "La date n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                        dateDebut = JOptionPane.showInputDialog("Veuillez donnez une date de début de création de l'épreuve au format aaaa-mm-jj");
                        matchFound = matcher.find();
                    }
                    String dateFin = JOptionPane.showInputDialog("Veuillez donnez une date de fin de création de l'épreuve au format aaaa-mm-jj");
                    if (!matchFound) {
                        JOptionPane.showMessageDialog(null, "La date n'est pas dans le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
                        dateDebut = JOptionPane.showInputDialog("Veuillez donnez une date de fin de création de l'épreuve au format aaaa-mm-jj");
                        matchFound = matcher.find();
                    }
                    Epreuve epreuve = new Epreuve(id, nom, dateDebut, dateFin);
                    vue.getTable().getModel().setValueAt(nom, ligne, 0);
                    vue.getTable().getModel().setValueAt(dateDebut, ligne, 1);
                    vue.getTable().getModel().setValueAt(dateFin, ligne, 2);
                    vue.getTable().getModel().setValueAt(0, ligne, 3); 
                    vue.addRow(epreuve);
                    JOptionPane.showConfirmDialog(null, "Colonne modifiée", "validation", JOptionPane.CLOSED_OPTION);   
                }
            }
        }
    }
}
