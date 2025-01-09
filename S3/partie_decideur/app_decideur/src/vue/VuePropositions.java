package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import controleur.ControleurProposition;
import modele.Application;
import modele.Groupe;
import modele.Proposition;

public class VuePropositions extends JPanel{
    private ImageIcon imageHome;
    private JLabel labelHome;
    private ArrayList<Proposition> propositions;
    private JPanel mainPanel;
    @SuppressWarnings("unused")
    public VuePropositions(ArrayList<Proposition> propositions) {
        this.propositions = propositions;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding autour du panel principal
        this.setBackground(new Color(240, 240, 240)); // Couleur de fond légèrement grise
        
        Groupe g = Application.getGroupById(Application.groupeActif);
        JPanel panelGroupe = new JPanel();
        imageHome = Application.createScaledIcon("image/logo.png", 20, 20);
        labelHome = new JLabel(imageHome);
        panelGroupe.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel idGLabel = createStyledLabel(Integer.toString(g.getId()));
        JLabel nomGLabel = createStyledLabel(g.getNom());
        JLabel budgetGLabel = createStyledLabel(Float.toString(g.getBudget()));
        ControleurProposition controleur = new ControleurProposition(this, g);
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        labelHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Application.cardLayout.show(Application.mainContainer, "Menu");
                Application.frame.revalidate();
                Application.frame.repaint();
            }
        });
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.addItem("Plus de prop par thématique");
        comboBox.addItem("la plus grande satisfaction de chaque thématique");
        comboBox.addItem("max satisfaction et min budget");
        comboBox.setMaximumSize(new Dimension(100, 30));
        comboBox.addItemListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            switch (selected) {
                case "Plus de prop par thématique":
                    controleur.trierPlusDePropositionsParthematique();
                    break;
                case "la plus grande satisfaction de chaque thématique":
                    controleur.trierPropositionsMaxSatisfaction();
                    break;
                case "max satisfaction et min budget":
                    controleur.trierPropositionsParBudgetEtSatisfaction();
                    break;
                default:
                    break;
            }
            this.propositions = controleur.getPropositions();
            this.afficherTri();
            
        });
        this.add(labelHome); 
        this.add(comboBox);
        this.afficherTri();
        this.add(mainPanel);
        
        
    }
	
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16)); // Police personnalisée
        label.setForeground(Color.WHITE); // Texte blanc pour contraste
        return label;
    }
        
    private void afficherTri() {
        mainPanel.removeAll();
        for (Proposition proposition : this.propositions) {
            // Créer le panel du groupe
            JPanel propositionsPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Dessiner un fond arrondi avec une couleur personnalisée
//                    g2.setColor(Color.decode(groupe.getCouleur()));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Coins arrondis
                }
            };
            
            propositionsPanel.setLayout(new GridLayout(1, 3, 10, 10)); // Espacement entre les colonnes
            propositionsPanel.setMaximumSize(new Dimension(1200, 100)); // Limite la largeur
            propositionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding interne
            propositionsPanel.setOpaque(false); // Laisser la couleur de fond personnalisée s'afficher

            // Créer les labels avec une meilleure esthétique
            JLabel nomLabel = createStyledLabel(proposition.getTitre());
            JLabel budgetLabel = createStyledLabel("Budget: " + proposition.getBudget() + " €");
            JLabel membresLabel = createStyledLabel(String.format("%.2f", proposition.getIndiceSatisfaction()));

            // Ajouter les labels au panneau
            propositionsPanel.add(nomLabel);
            propositionsPanel.add(budgetLabel);
            propositionsPanel.add(membresLabel);
            
            // Ajouter mouse Listener
            propositionsPanel.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		Application.mainContainer.add(new VueProposition(proposition),"détail Proposition");
                    Application.cardLayout.show(Application.mainContainer, "détail Proposition");
                    Application.frame.revalidate();
                    Application.frame.repaint();
            	}
            });
            // Ajouter le panneau à la vue
            mainPanel.add(propositionsPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacement entre les panneaux
        }
        // Revalider et repeindre le cadre
        
        Application.frame.revalidate();
        Application.frame.repaint();
    }
    
}
