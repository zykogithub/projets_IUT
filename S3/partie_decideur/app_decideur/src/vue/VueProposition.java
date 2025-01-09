package vue;

import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;

import modele.Application;
import modele.Proposition;

public class VueProposition extends JPanel {
    private JPanel mainPanel;
    private JLabel titre;
    private JLabel description;
    private JLabel budget;
    private ImageIcon imageRetour;
    private ImageIcon imageHome;
    private JLabel labelHome;
    private JLabel labelRetour;
    private JPanel navigationJPanel;

    public VueProposition(Proposition proposition) {
        // Affectation des propriétés
        this.mainPanel = new JPanel(new GridLayout(5, 1));
        this.titre = new JLabel(proposition.getTitre());
        this.description = new JLabel(proposition.getDescription());
        this.budget = new JLabel(String.valueOf(proposition.getBudget()));
        imageRetour =  Application.createScaledIcon("image/back.png", 20, 20);
        imageHome =  Application.createScaledIcon("image/logo.png", 20, 20);
        labelRetour = new JLabel(imageRetour);
        labelHome = new JLabel(imageHome);
        navigationJPanel =  new JPanel();


        // Personnalisation des composants
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        titre.setBackground(Color.BLUE);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        budget.setFont(new Font("Arial", Font.BOLD, 14));
        navigationJPanel.setLayout(new BoxLayout(navigationJPanel, BoxLayout.Y_AXIS));

        labelHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Application.cardLayout.show(Application.mainContainer, "Menu");
                Application.frame.revalidate();
                Application.frame.repaint();
            }
        });
        labelRetour.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Application.cardLayout.show(Application.mainContainer, "page propositions");
                Application.frame.revalidate();
                Application.frame.repaint();
            }
        });

        
        

        // Ajout des composants avec espaces blancs
        this.navigationJPanel.add(labelHome);
        this.navigationJPanel.add(labelRetour);
        this.mainPanel.add(navigationJPanel);
        this.mainPanel.add(Box.createVerticalStrut(10)); // Ajout d'un espace blanc vertical
        this.mainPanel.add(titre);
        this.mainPanel.add(Box.createVerticalStrut(10)); // Ajout d'un espace blanc vertical
        this.mainPanel.add(description);
        this.mainPanel.add(Box.createVerticalStrut(10)); // Ajout d'un espace blanc vertical
        this.mainPanel.add(budget);

        // Ajout du mainPanel au JPanel principal
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        
    }
    
}
