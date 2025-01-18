package view;

import javax.swing.*;

import model.Application;
import model.Groupe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;

public class VueGroupes extends JPanel {

    public VueGroupes(ArrayList<Groupe> groupes) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(new Color(240, 240, 240));
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
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                }
            };
            
            groupePanel.setLayout(new GridLayout(1, 3, 10, 10));
            groupePanel.setMaximumSize(new Dimension(1200, 100));
            groupePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            groupePanel.setOpaque(false);

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
            		Application.mainContainer.add(new JScrollPane(new VuePropositions(groupe.getPropositions())),"page propositions");
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
            this.add(Box.createRigidArea(new Dimension(0, 15)));
            
        }
    }

    // Méthode utilitaire pour créer des labels stylisés
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        return label;
    }
}