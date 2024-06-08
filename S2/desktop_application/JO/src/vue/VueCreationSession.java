package vue;

import javax.swing.*;
import modeles.Planning;


import java.awt.*;
import java.awt.event.ActionListener;


/**
 * La classe VueCreationSession représente l'interface graphique pour créer une nouvelle session, et l'ajouter au planning.
 *
 * @author Ambre VIAU
 */
public class VueCreationSession extends JPanel {
    JLabel nom;
    JLabel epreuve;
    JLabel id;
    JLabel date;
    JLabel horaire;
    JTextField nomField;
    JTextField epreuveField;
    JTextField idField;
    JTextField dateField;
    JTextField horaireField;
    JButton ajouter;
    Planning planning;
    VuePlanning vuePlanning;

    static public final String ACTION_AJOUTER = "Créer et ajouter la session";

    /**
     * Constructeur de la classe VueCreationSession.
     * Initialise l'interface avec un planning et une vue de planning donnés.
     *
     * @param planning    le planning auquel ajouter la session
     * @param vuePlanning la vue de planning associée
     * @author Ambre VIAU
     */
    public VueCreationSession(Planning planning, VuePlanning vuePlanning) {
        nom = new JLabel("Création d'une nouvelle session");
        nom.setHorizontalAlignment(JLabel.CENTER);
        nom.setFont(new Font("Serif", Font.BOLD, 20));
        epreuve = new JLabel("Id de l'épreuve : ");
        id = new JLabel("Id de la session : ");
        date = new JLabel("Date de la session : ");
        horaire = new JLabel("Horaire de la session : ");
        nomField = new JTextField(20);
        epreuveField = new JTextField(20);
        idField = new JTextField(20);
        dateField = new JTextField(20);
        horaireField = new JTextField(20);
        ajouter = new JButton("Créer et ajouter la session");
        ajouter.setFont(new Font("Serif", Font.BOLD, 16));
        ajouter.setActionCommand(ACTION_AJOUTER);
        this.planning = planning;
        this.vuePlanning = vuePlanning;
        
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(4, 2));
        add(nom, BorderLayout.NORTH);
        panelCentral.add(epreuve);
        panelCentral.add(epreuveField);
        panelCentral.add(id);
        panelCentral.add(idField);
        panelCentral.add(date);
        panelCentral.add(dateField);
        panelCentral.add(horaire);
        panelCentral.add(horaireField);
        add(panelCentral, BorderLayout.CENTER);
        add(ajouter, BorderLayout.SOUTH);
    }

    /**
     * Constructeur par défaut de la classe VueCreationSession.
     * Initialise l'interface sans planning ni vue de planning.
     *
     * @author Ambre VIAU
     */
    public VueCreationSession() {
        nom = new JLabel("Création d'une nouvelle session");
        nom.setHorizontalAlignment(JLabel.CENTER);
        nom.setFont(new Font("Serif", Font.BOLD, 20));
        epreuve = new JLabel("Id de l'épreuve : ");
        id = new JLabel("Id de la session : ");
        date = new JLabel("Date de la session : ");
        horaire = new JLabel("Horaire de la session : ");
        nomField = new JTextField(20);
        epreuveField = new JTextField(20);
        idField = new JTextField(20);
        dateField = new JTextField(20);
        horaireField = new JTextField(20);
        ajouter = new JButton("Créer et ajouter la session");
        ajouter.setFont(new Font("Serif", Font.BOLD, 16));
        ajouter.setActionCommand(ACTION_AJOUTER);
        this.planning = null;
        this.vuePlanning = null;
        
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(4, 2));
        add(nom, BorderLayout.NORTH);
        panelCentral.add(epreuve);
        panelCentral.add(epreuveField);
        panelCentral.add(id);
        panelCentral.add(idField);
        panelCentral.add(date);
        panelCentral.add(dateField);
        panelCentral.add(horaire);
        panelCentral.add(horaireField);
        add(panelCentral, BorderLayout.CENTER);
        add(ajouter, BorderLayout.SOUTH);
    }

    /**
     * Retourne le bouton "ajouter".
     *
     * @return le bouton "ajouter"
     * @author Ambre VIAU
     */
    public JButton getAjouterButton() {
        return ajouter;
    }

    /**
     * Retourne le champ de texte pour le nom.
     *
     * @return le champ de texte pour le nom
     * @author Ambre VIAU
     */
    public JTextField getNomField() {
        return nomField;
    }

    /**
     * Retourne le champ de texte pour l'épreuve.
     *
     * @return le champ de texte pour l'épreuve
     * @author Ambre VIAU
     */
    public JTextField getEpreuveField() {
        return epreuveField;
    }

    /**
     * Retourne le champ de texte pour l'identifiant de la session.
     *
     * @return le champ de texte pour l'identifiant de la session
     * @author Ambre VIAU
     */
    public JTextField getIdField() {
        return idField;
    }

    /**
     * Retourne le champ de texte pour la date.
     *
     * @return le champ de texte pour la date
     * @author Ambre VIAU
     */
    public JTextField getDateField() {
        return dateField;
    }

    /**
     * Retourne le champ de texte pour l'horaire.
     *
     * @return le champ de texte pour l'horaire
     * @author Ambre VIAU
     */
    public JTextField getHoraireField() {
        return horaireField;
    }

    /**
     * Méthode principale pour exécuter l'interface de création de session.
     *
     * @param args les arguments de la ligne de commande
     * @author Ambre VIAU
     */
    public static void main(String[] args) {
        JFrame fenetre = new JFrame("Création session des Jeux Olympiques");

        fenetre.setSize(400, 300);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VueCreationSession p = new VueCreationSession();
        fenetre.add(p);

        fenetre.setVisible(true);
        // Empêcher à l'utilisateur de changer la taille de la fenêtre
        fenetre.addComponentListener(null);
    }

    /**
     * Définit un écouteur d'action pour le bouton "ajouter".
     *
     * @param l l'écouteur d'action à définir
     * @author Ambre VIAU
     */
    public void setListener(ActionListener l) {
        this.ajouter.addActionListener(l);
    }
}
