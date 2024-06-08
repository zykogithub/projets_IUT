package vue;

import modeles.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Controleur.Enregistrer;
import Controleur.EpreuveControleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Cette classe représente la vue des épreuves des Jeux Olympiques.
 *
 * @author Yousra HACHMI
 */
public class VueEpreuve extends JPanel {
    // Table model pour les épreuves
    private EpreuveTableModel epreuveTableModel;

    // JTable pour afficher les épreuves
    private JTable table;

    // création des boutons pour toutes les épreuves
    JButton buttonSupp;
    JButton buttonAjout;
    JButton buttonModif;
    JButton enregistrer,retour;

    /**
     * Constructeur de la vue des épreuves.
     *
     * @param epreuves La ArrayListe des épreuves à afficher.
     */
    public VueEpreuve(ArrayList<Epreuve> epreuves)  {
        // Initialisation du modèle de table avec les données des épreuves
        epreuveTableModel = new EpreuveTableModel(epreuves);
        table = new JTable(epreuveTableModel);

        // création des boutons
        buttonSupp = new JButton("Supprimer");
        buttonAjout = new JButton("Ajouter");
        buttonModif = new JButton("Modifier");
        enregistrer = new JButton("Enregistrer");
        retour = new JButton("Retour");

        // panel de controle qui contiennent les boutons
        JPanel buttons = new JPanel();
        buttons.add(buttonSupp);
        buttons.add(buttonAjout);
        buttons.add(buttonModif);
        buttons.add(enregistrer);
        buttons.add(retour);


        
        

        // BorderLayout pour organiser les composants
        setLayout(new BorderLayout());

        // Ajout du JScrollPane contenant le JTable et du panel des boutons à la vue principale
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);


        
    }

    /**
     * Cette classe interne représente le modèle de table pour les épreuves.
     */
    
    private class EpreuveTableModel extends AbstractTableModel   {
        private final String[] columnNames = {"Nom", "Date début", "Date fin", "Classement"};
        private final ArrayList<Epreuve> epreuves;

        /**
         * Constructeur du modèle de table des épreuves.
         *
         * @param epreuves La ArrayListe des épreuves à afficher dans le tableau.
         */
        public EpreuveTableModel(ArrayList<Epreuve> epreuves) {
            this.epreuves = epreuves;
        }

        @Override
        public int getRowCount() {
            return epreuves.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Epreuve epr = epreuves.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return epr.getNom();
                case 1:
                    return epr.getDateDebut();
                case 2:
                    return epr.getDateFin();
                case 3:
                    return epr.getClassement();
            }
            return null;
        }
        
        /*
         * fonction pour changer la valeur d'une case du tableua
         * @author Naherry MOHAMED DAROUECHE
         */
        @Override
        
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Epreuve epr = epreuves.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    epr.setNom(((String)aValue));
                    break;
                case 1:
                    epr.setDateDebut(((String)aValue));
                    break;
                case 2:
                    epr.setDateFin(((String)aValue));
                    break;
                case 3:
                    epr.setClassement(((Integer)aValue));
                    break;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }


    /**
     * Méthode principale pour l'exécution de l'application.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args)   {
        JFrame fenetre = new JFrame("Epreuves des Jeux Olympiques");
        fenetre.setSize(900, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        // Créer quelques épreuves pour démonstration
        ArrayList<Epreuve> epreuves = new ArrayList<>();
        epreuves.add(new Epreuve(1, "100m", "2024-07-24", "2024-07-24"));
        epreuves.add(new Epreuve(2, "Saut en longueur", "2024-07-25", "2024-07-25"));
        epreuves.add(new Epreuve(3, "Marathon", "2024-07-26", "2024-07-26"));
        epreuves.add(new Epreuve(9, "10000m", "2024-08-01", "2024-08-01"));
        epreuves.add(new Epreuve(10, "110m haies", "2024-08-02", "2024-08-02"));
        epreuves.add(new Epreuve(113, "Gymnastique - Barres asymétriques femmes", "2024-08-05", "2024-08-06"));
        epreuves.add(new Epreuve(114, "Tir - Carabine à air comprimé 10m hommes", "2024-08-06", "2024-08-07"));
        epreuves.add(new Epreuve(12, "3000m steeple", "2024-08-04", "2024-08-04"));
        epreuves.add(new Epreuve(13, "Relais 4x100m", "2024-08-05", "2024-08-05"));
        epreuves.add(new Epreuve(101, "Athlétisme - 100m hommes", "2024-07-24", "2024-07-25"));
        epreuves.add(new Epreuve(102, "Natation - 200m nage libre femmes", "2024-07-25", "2024-07-26"));
        epreuves.add(new Epreuve(103, "Gymnastique - Sol hommes", "2024-07-26", "2024-07-27"));
        epreuves.add(new Epreuve(104, "Cyclisme - Course en ligne femmes", "2024-07-27", "2024-07-28"));
        epreuves.add(new Epreuve(105, "Tennis - Simple femmes", "2024-07-28", "2024-07-29"));
        epreuves.add(new Epreuve(4, "200m", "2024-07-27", "2024-07-27"));
        epreuves.add(new Epreuve(5, "400m", "2024-07-28", "2024-07-28"));
        epreuves.add(new Epreuve(8, "5000m", "2024-07-31", "2024-07-31"));
        epreuves.add(new Epreuve(106, "Tir à l'arc - Équipe mixte", "2024-07-29", "2024-07-30"));
        epreuves.add(new Epreuve(107, "Boxe - Poids lourd hommes", "2024-07-30", "2024-07-31"));
        epreuves.add(new Epreuve(108, "Football - Finale hommes", "2024-07-31", "2024-08-01"));
        epreuves.add(new Epreuve(6, "800m", "2024-07-29", "2024-07-29"));
        epreuves.add(new Epreuve(7, "1500m", "2024-07-30", "2024-07-30"));
        epreuves.add(new Epreuve(109, "Haltérophilie - 81kg femmes", "2024-08-01", "2024-08-02"));
        epreuves.add(new Epreuve(110, "Volley-ball - Finale femmes", "2024-08-02", "2024-08-03"));
        
        
        VueEpreuve vue = new VueEpreuve(epreuves);
        
        EpreuveControleur epreuve = new EpreuveControleur(vue);
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
        vue.enregistrer.addActionListener(new Enregistrer(vue.epreuveTableModel.epreuves,fenetre));
        fenetre.add(vue);
        fenetre.setVisible(true);
        // Empêcher à l'utilisateur de changer la taille de la fenêtre
        fenetre.setResizable(false);
    }

    public void addRow(Epreuve epreuve) {
        epreuveTableModel.epreuves.add(epreuve);
        epreuveTableModel.fireTableRowsInserted(ALLBITS, ABORT);
    }

    public Object getObjectRow() {
        if (table.getSelectedRow()==-1) {
            return null;
        }
        else{
            return epreuveTableModel.epreuves.get(table.getSelectedRow());
        }
    }

    public void removeRow(Epreuve epreuve) {
         epreuveTableModel.epreuves.remove(epreuve);
         epreuveTableModel.fireTableRowsDeleted(ALLBITS, ABORT);
    }

    public JButton getButtonAjout() {
        return this.buttonAjout; 
    }

    public JButton getButtonModif() {
        return this.buttonModif;
    }

    public JButton getButtonSupp() {
        return this.buttonSupp;
    }
    public JTable getTable(){
        return table;
    }
}

      