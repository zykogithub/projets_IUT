package vue;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controleur.Enregistrer;

/**
 * Classe Menu pour exécuter toute l'application.
 * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
 */
public class Menu extends JPanel  {

    private JButton bouttonEquipe, bouttonEpreuve, bouttonPlannig, quitter, ouvir;
    private Image image;

    /**
     * Méthode principale pour exécuter l'application.
     *
     * @param args les arguments de la ligne de commande
     * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");
        Menu menu = new Menu();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        frame.add(menu);
        frame.setIconImage(menu.image);
        frame.setResizable(false);
        frame.setVisible(true);
        
        // Ajout des ActionListeners pour les boutons
        menu.bouttonEpreuve.addActionListener(new ActionListener() {
            /**
             * Action à effectuer lors du clic sur le bouton Epreuve.
             *
             * @param e l'événement d'action
             * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                VueEpreuve.main(null);
            }
        });
        
        menu.bouttonPlannig.addActionListener(new ActionListener() {
            /**
             * Action à effectuer lors du clic sur le bouton Planning.
             *
             * @param e l'événement d'action
             * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                VuePlanning.main(null);
            }
        });
        
        menu.bouttonEquipe.addActionListener(new ActionListener() {
            /**
             * Action à effectuer lors du clic sur le bouton Équipe.
             *
             * @param e l'événement d'action
             * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                VueEquipe.main(null);
            }
        });
        
        menu.quitter.addActionListener(new ActionListener() {
            /**
             * Action à effectuer lors du clic sur le bouton Quitter.
             *
             * @param e l'événement d'action
             * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean quitter = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter", "Quitter", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION;
                if (quitter) frame.dispose();
            }
        });
        menu.ouvir.addActionListener(new Enregistrer(frame));
    }

    /**
     * Constructeur de la classe Menu.
     * Initialise les boutons et leurs actions.
     * @author Naherry MOHAMED DAROUECHE : Naherry MOHAMED DAROUECHE
     */
    public Menu() {
        this.bouttonEquipe = new JButton("Gérer les équipes");
        this.bouttonEpreuve = new JButton("Gérer les épreuves");
        this.bouttonPlannig = new JButton("Gérer le planning");
        this.quitter = new JButton("Quitter");
        this.ouvir = new JButton("Ouvrir");

        bouttonEpreuve.setSize(100, 100);
        bouttonEquipe.setSize(100, 100);
        bouttonPlannig.setSize(100, 100);
        add(bouttonEpreuve);
        add(bouttonEquipe);
        add(bouttonPlannig);
        add(quitter);
        add(ouvir);
        
    }
}
