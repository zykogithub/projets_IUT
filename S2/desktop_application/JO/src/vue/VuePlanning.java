package vue;

import modeles.*;
import javax.swing.*;



import Controleur.ControleurPlanning;

import Controleur.Enregistrer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe VuePlanning représente l'interface graphique pour visualiser et gérer le planning des sessions.
 * Elle contient des boutons pour ajouter et supprimer des sessions et organise les sessions dans un panel.
 * 
 * @author Lyane Legernard et Ambre VIAU
 */
public class VuePlanning extends JPanel {
    // Constantes ACTION pour la gestion des événements
    static public final String ACTION_AJOUTER = "AJOUTER";
    static public final String ACTION_SUPPRIMER = "SUPPRIMER";
    
    // Référence vers la classe modèle Planning
    Planning planning;
    
    // Panel qui contient les éléments et organise la vue
    JPanel sessionPanel;
    JPanel selectedPanel;
    
    // Création des boutons pour tous les plannings
    JButton buttonSupp;
    JButton buttonAjout;
    JButton enregistrer,retour;

    // 
    VueCreationSession vueSession;

    /**
     * Constructeur de la vue pour initialiser les composants de l'interface.
     *
     * @param planning Le planning des sessions.
     */
    public VuePlanning(Planning planning) {
        this.planning = planning;
        vueSession = new VueCreationSession(planning,this);

        // Création des boutons
        buttonSupp = new JButton("Supprimer");
        buttonAjout = new JButton("Ajouter");
        enregistrer = new JButton("Enregistrer");
        retour = new JButton("Retour");

        // Préparation de la gestion des clics des boutons
        buttonSupp.setActionCommand(ACTION_SUPPRIMER);
        buttonAjout.setActionCommand(ACTION_AJOUTER);

        // Panel de contrôle qui contient les boutons
        JPanel buttons = new JPanel();
        buttons.add(buttonSupp);
        buttons.add(buttonAjout);
        buttons.add(enregistrer);
        buttons.add(retour);

        
        
        // BorderLayout pour organiser les composants
        setLayout(new BorderLayout());

        // Panel organisateur qui contiendra les sessions
        sessionPanel = new JPanel();
        sessionPanel.setLayout(new GridLayout(0, 1)); // 0 signifie un nombre de lignes flexible

        // Ajout des panels des sessions et des boutons à la vue principale
        add(sessionPanel, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);


    }


    /**
     * Méthode pour ajouter une session à la vue du planning.
     *
     * @param ses La session à ajouter.
     */
    public void ajouterSession(Session ses) {
        
        JPanel panelSession = new JPanel();
        panelSession.setLayout(new GridLayout(1, 3));
        
        
        panelSession.setBorder(BorderFactory.createLineBorder(Color.BLUE));
     
        panelSession.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedPanel != null) {
                    selectedPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                }
                selectedPanel = panelSession;
                panelSession.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        });

        if (ses.getEpreuve() != null) {
            panelSession.add(new JLabel(ses.getEpreuve().getNom() + "  "));
        } else {
            panelSession.add(new JLabel("Cette session n'a pas d'épreuve    "));
        }
       
        panelSession.add(new JLabel(ses.getDateSession()));
        panelSession.add(new JLabel(ses.getHoraireSession()));
        
        
        sessionPanel.add(panelSession);
        sessionPanel.revalidate();
        sessionPanel.repaint();
    }

    /**
     * Méthode pour supprimer une session de la vue du planning.
     *
     * @param panel Le panel représentant la session à supprimer.
     * @Lyane Legernard
     */

    public void supprimerSessionSelectionnee() {
        if (selectedPanel != null) {
            sessionPanel.remove(selectedPanel);
            selectedPanel = null;
            sessionPanel.revalidate();
            sessionPanel.repaint();
        }
    }
    

    /**
     * Méthode principale pour tester l'affichage de la vue du planning.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Planning des Jeux Olympiques");

        fenetre.setSize(900, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Planning p = new Planning();
        
        
        // Création des épreuves
        Epreuve e1 = new Epreuve(1, "100m Sprint", "2024-07-26", "2024-07-26");
        Epreuve e2 = new Epreuve(2, "Long Jump", "2024-07-27", "2024-07-27");
        Epreuve e3 = new Epreuve(3, "Swimming 200m Freestyle", "2024-07-28", "2024-07-28");
        
        // Création des sessions avec des exemples de dates et horaires
        Session s1 = new Session(1, "2024-07-26", "10:00");
        s1.setEpreuve(e1);
        Session s2 = new Session(2, "2024-07-27", "12:00");
        s2.setEpreuve(e2);
        Session s3 = new Session(3, "2024-07-28", "14:00");
        s3.setEpreuve(e3);
        Session s4 = new Session(4, "2024-07-29", "16:00");
        s4.setEpreuve(e1);
        Session s5 = new Session(5, "2024-07-30", "18:00");
        s5.setEpreuve(e2);
        
        VuePlanning vue = new VuePlanning(p);

        
        vue.ajouterSession(s1);
        vue.ajouterSession(s2);
        vue.ajouterSession(s3);
        vue.ajouterSession(s4);
        vue.ajouterSession(s5);

        vue.enregistrer.addActionListener(new Enregistrer(vue.planning,fenetre));
        
        ControleurPlanning e = new ControleurPlanning(vue.planning,vue,vue.vueSession,fenetre);
        vue.buttonSupp.addActionListener(e);
        vue.buttonAjout.addActionListener(e);
        fenetre.add(vue);
        fenetre.setVisible(true);
        // Empêcher à l'utilisateur de changer la taille de la fenêtre
        fenetre.setResizable(false);
        vue.retour.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le fichier", "Question",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Veuillez appuyer sur le boutton enregistrer avant de quitter.","information",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    fenetre.dispose();
                    Menu.main(null);
                }   
            }
        });
    }


    public JButton getButtonSupp() {
        return buttonSupp;
    }


    public JButton getButtonAjout() {
        return buttonAjout;
    }
}
