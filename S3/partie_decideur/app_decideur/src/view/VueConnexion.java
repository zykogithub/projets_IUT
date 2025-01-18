package view;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

// Classe VueConnexion pour créer l'interface utilisateur
public class VueConnexion extends JPanel{
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton connexionButton;
    private JLabel errorMessageLabel;

    public VueConnexion() {
        // Initialisation du panneau avec un layout centré
        Border contour = new LineBorder(Color.black, 3,true);
        Border padding = new EmptyBorder(24,25,24,24);
        this.setBorder(new CompoundBorder(contour, padding));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Champs email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(emailLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        emailField = new JTextField(20);
        emailField.setMaximumSize(new Dimension(500, 40));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(emailField);

        // Espacement
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Champs mot de passe
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(passwordLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(500, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(passwordField);

        // Espacement
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Message d'erreur
        errorMessageLabel = new JLabel("");
        errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        errorMessageLabel.setForeground(Color.RED);
        this.add(errorMessageLabel);

        // Espacement
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        // Bouton connexion
        connexionButton = new JButton("Connexion");
        connexionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        connexionButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(connexionButton);

        // Espacement
        this.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    // Accesseurs pour récupérer les champs, le bouton et le message d'erreur
    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getConnexionButton() {
        return connexionButton;
    }

    public JLabel getErrorMessageLabel() {
        return errorMessageLabel;
    }
}