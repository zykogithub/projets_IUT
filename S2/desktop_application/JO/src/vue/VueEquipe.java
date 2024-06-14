package vue;

import modeles.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Controleur.ControleurPlanning;
import Controleur.Enregistrer;
import Controleur.EquipeControleur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

/**
 * Cette classe représente la vue des équipes des Jeux Olympiques.
 *
 * @author Yousra HACHMI
 */
public class VueEquipe extends JPanel  {
    // Table model pour les équipes
    private EquipeTableModel equipeTableModel;

    // JTable pour afficher les équipes
    private JTable table;

    // création des boutons pour toutes les équipes
    JButton buttonSupp;
    JButton buttonAjout;
    JButton buttonModif;
    JButton enregistrer;
    JButton retour;

    /**
     * Constructeur de la vue des équipes.
     *
     * @param equipes La ArrayListe des équipes à afficher.
     */
    public VueEquipe(ArrayList<Equipe> equipes) {
        // Initialisation du modèle de table avec les données des équipes
        equipeTableModel = new EquipeTableModel(equipes);
        table = new JTable(equipeTableModel);

        // création des boutons
        buttonSupp = new JButton("Supprimer");
        buttonAjout = new JButton("Ajouter");
        buttonModif = new JButton("Modifier");
        enregistrer = new JButton("Enregistrer");;
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
     * Cette classe interne représente le modèle de table pour les équipes.
     */
    private class EquipeTableModel extends AbstractTableModel  {
        private final String[] columnNames = {"Nom", "Année de Création", "Identifiant"};
        private final ArrayList<Equipe> equipes;

        /**
         * Constructeur du modèle de table des équipes.
         *
         * @param equipes La ArrayListe des équipes à afficher dans le tableau.
         */
        public EquipeTableModel(ArrayList<Equipe> equipes) {
            this.equipes = equipes;
        }
        

        @Override
        public int getRowCount() {
            return equipes.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Equipe equipe = equipes.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return equipe.getNom();
                case 1:
                    return equipe.getAnneeCreationEquipe();
                case 2:
                    return equipe.getIdentifiant();
            }
            return null;
        }
    

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
        
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Equipe equipe = equipes.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    if(aValue instanceof String)equipe.setNom(((String)aValue));
                    break;
                
                case 1:
                    if(aValue instanceof Integer)equipe.setAnneeCreation((Integer)aValue);
                    break;
                case 2:
                if(aValue instanceof Integer)equipe.setIdentifiant((Integer)aValue);
                    break;
                
                default:
                    break;
                
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }   
    }
    /**
    Supprime une équipe à la table 
    @param equipe l'équipe à supprimer
    @auth Naherry MOHAMED DAROUECHE
     */
    public void removeRow(Equipe equipe) {
        equipeTableModel.equipes.remove(equipe);
        equipeTableModel.fireTableRowsDeleted(ALLBITS, ABORT);
    }
    public void afficherFenetre(){
        JFrame fenetre = new JFrame("Epreuves des Jeux Olympiques");
        fenetre.setSize(900, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.add(this);
        fenetre.setVisible(true);
        // Empêcher à l'utilisateur de changer la taille de la fenêtre
        fenetre.setResizable(false);
        retour.addActionListener(new ActionListener() {

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
        enregistrer.addActionListener(new Enregistrer(equipeTableModel.equipes,fenetre));
        buttonAjout.addActionListener(new EquipeControleur(this));
        buttonSupp.addActionListener(new EquipeControleur(this));
        buttonModif.addActionListener(new EquipeControleur(this));
        

    }
    /**
    Ajoute une équipe à la table
    @param equipe l'équipe à ajouter
    @auth Naherry MOHAMED DAROUECHE
     */
    public void addRow(Equipe equipe) {
        equipeTableModel.equipes.add(equipe);
        equipeTableModel.fireTableRowsInserted(ALLBITS, ABORT);
    }
    /**
    Retourne l'équipe à la colonne sélectionné sinon null
    @auth Naherry MOHAMED DAROUECHE
     */
    public Object getObjectRow(){
        if (table.getSelectedRow()==-1) {
            return null;
        }
        else{
            return equipeTableModel.equipes.get(table.getSelectedRow());
        }
    }


    /**
     * Méthode principale pour l'exécution de l'application.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
    	
    	ArrayList<Equipe> equipes = new ArrayList<>();
        VueEquipe vue = new VueEquipe(equipes);
        EquipeControleur controleur = new EquipeControleur(vue);
        JFrame fenetre = new JFrame("Equipes des Jeux Olympiques");
        fenetre.setSize(900, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer quelques équipes pour afficher l'exemple
        
        equipes.add(new Equipe(10024, "USA Basketball", 1936));
        equipes.add(new Equipe(992, "Canada Hockey", 1920));
        equipes.add(new Equipe(76, "Brazil Football", 1900));
        equipes.add(new Equipe(2077, "Australia Swimming", 1908));
        equipes.add(new Equipe(105, "Japan Judo", 1964));
        equipes.add(new Equipe(4378, "Germany Athletics", 1896));
        equipes.add(new Equipe(974, "Kenya Athletics", 1956));
        equipes.add(new Equipe(2524, "Russia Gymnastics", 1952));
        equipes.add(new Equipe(9172, "China Table Tennis", 1988));
        equipes.add(new Equipe(3, "France Handball", 1935));
        equipes.add(new Equipe(1022, "UK Rowing", 1900));
        equipes.add(new Equipe(20045, "South Korea Archery", 1984));
        equipes.add(new Equipe(20453, "Italy Cycling", 1896));
        equipes.add(new Equipe(8, "Netherlands Field Hockey", 1928));
        equipes.add(new Equipe(7952, "Sweden Ice Hockey", 1920));
        equipes.add(new Equipe(7268, "Spain Football", 1913));
        equipes.add(new Equipe(84284, "Argentina Rugby", 1899));
        equipes.add(new Equipe(3713, "Portugal Athletics", 1924));
        equipes.add(new Equipe(481, "Mexico Boxing", 1925));
        equipes.add(new Equipe(81, "India Field Hockey", 1928));
        equipes.add(new Equipe(1973, "New Zealand Rugby", 1884));
        equipes.add(new Equipe(92767, "South Africa Cricket", 1889));
        equipes.add(new Equipe(3762, "Sweden Cross-Country Skiing", 1892));
        equipes.add(new Equipe(8373, "Norway Biathlon", 1960));
        
        
        vue.enregistrer.addActionListener(new Enregistrer(vue,fenetre));
        

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
    public JTable getTable() {
        return table;
    }
    public EquipeTableModel getEquipeTableModel() {
        return equipeTableModel;
    }
    public JButton getButtonSupp() {
        return buttonSupp;
    }
    public JButton getButtonAjout() {
        return buttonAjout;
    }
    public JButton getButtonModif() {
        return buttonModif;
    }
    public void modifyRow(Equipe equipe) {
        equipeTableModel.equipes.set(table.getSelectedRow(), equipe);
    }
    
    
}

        