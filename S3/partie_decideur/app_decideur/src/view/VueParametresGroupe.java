package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Application;
import model.Groupe;
import model.Thematique;

import java.awt.event.MouseEvent;

public class VueParametresGroupe extends JPanel {

    
    private JPanel panel;
    private Groupe groupe;
    private JLabel texteAnnuel;
    private JLabel ratioUtilisation;
    private JProgressBar barreBudget;
    private JPanel thematiquePanel;
    private JLabel titreLabel;
    private ImageIcon imageHome;
    private JLabel labelHome;
    private JPanel navigationPanel;

    public VueParametresGroupe(Groupe groupe) {
        this.panel = new JPanel();
        this.groupe = groupe;
        this.texteAnnuel = new JLabel("Budget annuel ");
        this.barreBudget = new JProgressBar(0,(int) this.groupe.getBudget());
        String budgetUtilise = String.valueOf(Groupe.budgetTotal(this.groupe.getPropositions()));
        ratioUtilisation = new JLabel(budgetUtilise + "€ / " + this.groupe.getBudget() + " €");
        titreLabel = new JLabel(this.groupe.getNom());
        this.thematiquePanel = new JPanel();
        imageHome =  Application.createScaledIcon("image/logo.png", 20, 20);
        labelHome = new JLabel(imageHome);
        navigationPanel = new JPanel();        
        
        labelHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Application.cardLayout.show(Application.mainContainer, "Menu");
                Application.frame.revalidate();
                Application.frame.repaint();
            }
        });
        barreBudget.setValue(((int)Groupe.budgetTotal(this.groupe.getPropositions())));
        barreBudget.setForeground(Color.RED);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        thematiquePanel.setLayout(new BoxLayout(thematiquePanel,BoxLayout.Y_AXIS));
        navigationPanel.setLayout(new BoxLayout(navigationPanel,BoxLayout.Y_AXIS));
        
        navigationPanel.add(labelHome);
        panel.add(navigationPanel);
        panel.add(titreLabel);
        panel.add(texteAnnuel);
        panel.add(ratioUtilisation);
        panel.add(barreBudget);
        for (Thematique thematique : this.groupe.getThematiques()) {
            JPanel panelThematique = new JPanel();
            JLabel texteMensuel = new JLabel(thematique.getNom());
            JProgressBar barreBudgetThematique = new JProgressBar(0,(int) thematique.getBudget());
            String budgetUtiliseThematique = String.valueOf(thematique.getBudget());
            JLabel ratioUtilisationThematique = new JLabel(budgetUtiliseThematique + "€ / " + this.groupe.getBudget() + " €");

            barreBudgetThematique.setValue(((int)thematique.getBudget()));
            barreBudgetThematique.setForeground(Color.YELLOW);
            panelThematique.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panelThematique.setBackground(new Color(240, 240, 240));
            panelThematique.setLayout(new BoxLayout(panelThematique, BoxLayout.Y_AXIS));
            
            panelThematique.add(texteMensuel);
            panelThematique.add(Box.createVerticalStrut(10));
            panelThematique.add(ratioUtilisationThematique);
            panelThematique.add(Box.createVerticalStrut(10));
            panelThematique.add(barreBudgetThematique);
            panelThematique.add(Box.createVerticalStrut(10));
            thematiquePanel.add(panelThematique);
            
        }
        panel.add(this.thematiquePanel);
        this.add(panel);
    }    

}
