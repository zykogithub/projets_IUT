package Controleur;

import java.awt.event.*;
import modeles.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import vue.*;

public class EquipeControleur implements ActionListener {

    private VueEquipe vue;

    /**
     * Implémentation de actionPerformed
     * @param e l'évènement à l'origine de l'action 
     * @author Naherry MOHAMED DAROUECHE
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boutton = (JButton)e.getSource();
            String action = boutton.getText();
            if (action.equals("Supprimer")) {
                if (vue.getObjectRow() instanceof Equipe) {
                    Equipe equipe = (Equipe)vue.getObjectRow();
                    vue.removeRow(equipe);
                    JOptionPane.showMessageDialog(null, "Colonne supprimée", "validation", JOptionPane.CLOSED_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune ligne sélectionnée", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else if (action.equals("Ajouter")) {
                String nom = JOptionPane.showInputDialog("Veuillez donnez un nouveau nom d'équipe");
                if (nom == null) {
                    JOptionPane.showMessageDialog(null, "Aucun nom n'a été fourni", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                String identifiant = JOptionPane.showInputDialog("Veuillez donnez un identifiant d'équipe");
                int id = Integer.parseInt(identifiant);
                String annee = JOptionPane.showInputDialog("Veuillez donnez une année de création d'équipe");
                int anneeCreation = Integer.parseInt(annee);
                Equipe equipe = new Equipe(id, nom, anneeCreation);
                vue.addRow(equipe);
                JOptionPane.showMessageDialog(null, "Colonne ajoutée", "validation", JOptionPane.CLOSED_OPTION);
            } else if (action.equals("Modifier")) {
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
                    String annee = JOptionPane.showInputDialog("Veuillez donnez une année de création d'équipe");
                    int anneeCreation = Integer.parseInt(annee);
                    vue.getTable().getModel().setValueAt(nom, ligne, 0);
                    vue.getTable().getModel().setValueAt(id, ligne, 2);
                    vue.getTable().getModel().setValueAt(anneeCreation, ligne, 1); 
                    vue.modifyRow(new Equipe(id, nom, anneeCreation));
                    JOptionPane.showMessageDialog(null, "Colonne modifiée", "validation", JOptionPane.CLOSED_OPTION);   
                }
            }
        }
    }
    
    /**
     * Construction d'un contrôleur avec comme paramètre une vue 
     * @param vue la vue en question
     * @author Naherry MOHAMED DAROUECHE
     */
    public EquipeControleur(VueEquipe vue) {
        this.vue = vue;
        vue.getButtonAjout().addActionListener(this);
        vue.getButtonModif().addActionListener(this);
        vue.getButtonSupp().addActionListener(this);
    }
}
