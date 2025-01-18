package controleur;
import java.awt.event.*;
import javax.swing.*;

import modele.Application;
import vue.VueConnexion;
import vue.VueGroupes;

public class ControleurConnexion {
    private VueConnexion vue;
    private JFrame fenetre;

    public ControleurConnexion(VueConnexion vue,JFrame fenetre) {
        this.vue = vue;
        this.fenetre=fenetre;

        // Ajout d'un ActionListener au bouton connexion
        vue.getConnexionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gererConnexion();
            }
        });
    }

    // Méthode pour gérer l'action du bouton connexion
    private void gererConnexion() {
        String email = vue.getEmailField().getText();
        String motDePasse = new String(vue.getPasswordField().getPassword());

        if (email.isEmpty() || motDePasse.isEmpty()) {
            vue.getErrorMessageLabel().setText("Veuillez remplir tous les champs.");
        } else {
            // Simuler une vérification des informations
        	int id_decideur = Application.checkInfos(email,motDePasse);
            if (id_decideur!=-1) {
                vue.getErrorMessageLabel().setText("");
                Application.groupes=Application.listeGroupe(id_decideur);
                Application.mainContainer.add(new VueGroupes(Application.groupes),"Menu");
                Application.cardLayout.show(Application.mainContainer, "Menu");
                fenetre.revalidate();
                fenetre.repaint();
                
            } else {
                vue.getErrorMessageLabel().setText("Email ou mot de passe incorrect.");
            }
        }
    }
}
