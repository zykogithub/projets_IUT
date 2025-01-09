package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueExemple extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public VueExemple() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create different panels
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("This is Panel 1"));
        JButton nextButton1 = new JButton("next 2");
        panel1.add(nextButton1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is Panel 2"));
        JButton nextButton2 = new JButton("Next 3");
        JButton backButton2 = new JButton( "back home",createScaledIcon("image/home.png",20,20));
        panel2.add(nextButton2);
        panel2.add(backButton2);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("This is Panel 3"));
        JButton backButton3 = new JButton( "back home",createScaledIcon("image/home.png",20,20));
        panel3.add(backButton3);

        // Add panels to main panel
        mainPanel.add(panel1, "Panel 1");
        mainPanel.add(panel2, "Panel 2");
        mainPanel.add(panel3, "Panel 3");

        // Add action listeners for buttons
        nextButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Panel 2");
            }
        });

        nextButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Panel 3");
            }
        });

        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Panel 1");
            }
        });

        backButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Panel 1");
            }
        });

        // Set up the frame
        add(mainPanel);
        setTitle("VueExemple");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
        
    final private Icon createScaledIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
        
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VueExemple().setVisible(true);
            }
        });
    }
}
