package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;

import modele.Application;
import modele.Groupe;

public class VueGroupes extends JPanel {

    public VueGroupes(ArrayList<Groupe> groupes) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding autour du panel principal
        this.setBackground(new Color(240, 240, 240)); // Couleur de fond légèrement grise
        for (Groupe groupe : groupes) {
            // Créer le panel du groupe
            JPanel groupePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Dessiner un fond arrondi avec une couleur personnalisée
                    g2.setColor(Color.decode(groupe.getCouleur()));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Coins arrondis
                }
            };
            
            groupePanel.setLayout(new GridLayout(1, 3, 10, 10)); // Espacement entre les colonnes
            groupePanel.setMaximumSize(new Dimension(1200, 100)); // Limite la largeur
            groupePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding interne
            groupePanel.setOpaque(false); // Laisser la couleur de fond personnalisée s'afficher

            // Créer les labels avec une meilleure esthétique
            JLabel nomLabel = createStyledLabel(groupe.getNom());
            JLabel membresLabel = createStyledLabel(groupe.getNombreDeMembres() + " membres");
            JLabel parametreLabel = new JLabel(Application.createScaledIcon("image/loupe.png", 30, 30));

            // Ajouter les labels au panneau
            groupePanel.add(membresLabel);
            groupePanel.add(nomLabel);
            groupePanel.add(parametreLabel);
            
            // Ajouter mouse Listener
            groupePanel.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		Application.groupeActif=groupe.getId();
            		Application.mainContainer.add(new VuePropositions(groupe.getPropositions()),"page propositions");
                    Application.cardLayout.show(Application.mainContainer, "page propositions");
                    Application.frame.revalidate();
                    Application.frame.repaint();
            	}            
            });
            parametreLabel.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		Application.mainContainer.add(new VueParametresGroupe(groupe),"page parametres");
                    Application.cardLayout.show(Application.mainContainer, "page parametres");
                    Application.frame.revalidate();
                    Application.frame.repaint();
            	}            
            });
            // Ajouter le panneau à la vue
            this.add(groupePanel);
            this.add(Box.createRigidArea(new Dimension(0, 15))); // Espacement entre les panneaux
            
        }
    }

    // Méthode utilitaire pour créer des labels stylisés
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16)); // Police personnalisée
        label.setForeground(Color.WHITE); // Texte blanc pour contraste
        return label;
    }
}