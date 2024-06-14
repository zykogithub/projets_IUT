package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modeles.Epreuve;
import modeles.Equipe;
import modeles.Planning;
import modeles.Session;
import vue.VueEpreuve;
import vue.VueEquipe;
import vue.VuePlanning;

/**
 * Classe Enregistrer pour enregistrer un fichier.
 * 
 * @author Naherry MOHAMED DAROUECHE
 */
public class Enregistrer implements ActionListener, Serializable {
    private Object modele;
    private JFrame fenetre;

    /**
     * Méthode actionPerformed pour gérer les actions des boutons.
     *
     * @param e l'événement d'action déclenché par un bouton
     * @author Naherry MOHAMED DAROUECHE
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            // si c'est le bouton retour, on demande si on veut enregistrer
            if ("Retour".equals(button.getText())) {
                String nomFichier = JOptionPane.showInputDialog("Veuillez donner le chemin vers le fichier et le fichier doit être au format .dat");
                File fichier = new File(nomFichier);
                if (fichier.exists()) {
                    // block try catch afin d'attraper les erreurs lancées par les
                    // fonctions enregistrer et enregistrerFichier
                    boolean enregistrementEffectuer = true;
                    try {
                        this.enregistrerFichier(fichier);
                    } catch (ClassNotFoundException | IOException e1) {
                        enregistrementEffectuer=false;
                    }
                    if (!enregistrementEffectuer) {
                        JOptionPane.showMessageDialog(null, "Fichier non enregistré");
                    } else {
                        JOptionPane.showMessageDialog(null, "Fichier enregistré");
                    }
                    Runnable action = JOptionPane.showConfirmDialog(null, "Voulez-revenir au menu") == JOptionPane.YES_NO_OPTION
                            ? () -> { vue.Menu.main(null); fenetre.dispose(); }
                            : () -> {};
                    action.run();
                }
            }
            // si c'est le bouton enregistrer, on enregistre le fichier
            else if ("Enregistrer".equals(button.getText())) {
                Boolean cliqueOui = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer le fichier", "question", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (cliqueOui) {
                    String nomFichier = JOptionPane.showInputDialog("Veuillez donner le chemin vers le fichier et le fichier doit être au format .dat");
                    File fichier = new File(nomFichier);
                    boolean enregistrementEffectuer = true;
                    if (fichier.exists()) {
                        try {
                            this.enregistrerFichier(fichier);
                        } catch (ClassNotFoundException | IOException e1) {
                            enregistrementEffectuer = false;
                        }
                        if (enregistrementEffectuer) {
                            JOptionPane.showMessageDialog(null, "Fichier enregistré");
                            Runnable action = JOptionPane.showConfirmDialog(null, "Voulez-revenir au menu") == JOptionPane.YES_NO_OPTION
                                    ? () -> { vue.Menu.main(null); fenetre.dispose(); }
                                    : () -> {};
                            action.run();
                        } else {
                            JOptionPane.showMessageDialog(null, "Fichier non enregistré");
                        }
                    } else {
                        try {
                            this.enregistrer(nomFichier);
                        } catch (ClassNotFoundException | IOException e1) {
                            enregistrementEffectuer = false;
                        }
                        if (enregistrementEffectuer && nomFichier.matches("^.*\\.dat$")) {
                            JOptionPane.showMessageDialog(null, "Fichier enregistré");
                            Runnable action = JOptionPane.showConfirmDialog(null, "Voulez-revenir au menu") == JOptionPane.YES_NO_OPTION
                                    ? () -> { vue.Menu.main(null); fenetre.dispose(); }
                                    : () -> {};
                            action.run();
                        } else if(!enregistrementEffectuer) {

                            JOptionPane.showMessageDialog(null, "Fichier non enregistré");
                        }
                    }
                }
            } else if ("Ouvrir".equals(button.getText())) 
            {
                String nomFichier = JOptionPane.showInputDialog("Veuillez donner le chemin vers le fichier et le fichier doit être au format .dat");
                File file = new File(nomFichier);
                if (file.exists()) {
                    try {
                        ouvrirFichier(file);
                    } catch (ClassNotFoundException | IOException e4) {
                        if (e4 instanceof ClassNotFoundException) {
                            JOptionPane.showMessageDialog(null, "Le fichier ne s'ouvre pas pour x", "erreur", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Le fichier ne s'ouvre pas pour y", "erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Le fichier n'existe pas", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * Enregistre un fichier existant.
     *
     * @param fichier le fichier à enregistrer
     * @throws IOException            si une erreur d'entrée/sortie se produit
     * @throws ClassNotFoundException si une classe ne peut pas être trouvée
     * @author Naherry MOHAMED DAROUECHE
     */
    private void enregistrerFichier(File fichier) throws IOException, ClassNotFoundException {
        boolean vaEcraser = JOptionPane.showConfirmDialog(null, "Voulez-vous écraser l'ancien fichier", "question", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
        if (vaEcraser) {
            Pattern pattern = Pattern.compile("^.*\\.dat$");
            Matcher matcher = pattern.matcher(fichier.getAbsolutePath());
            boolean matchFound = matcher.find();

            if (!matchFound) {
                JOptionPane.showMessageDialog(null, "Le fichier n'a pas le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    FileOutputStream enStream = new FileOutputStream(fichier);
                    ObjectOutputStream floOutputStream = new ObjectOutputStream(enStream);
                    floOutputStream.writeObject(modele);
                    floOutputStream.close();
                    enStream.close();
                    
                }catch (IOException e4){
                    e4.printStackTrace();
                }

            }
        }
    }

    /**
     * Enregistre un nouveau fichier.
     *
     * @param fichier le chemin du fichier à enregistrer
     * @throws IOException            si une erreur d'entrée/sortie se produit
     * @throws ClassNotFoundException si une classe ne peut pas être trouvée
     * @author Naherry MOHAMED DAROUECHE
     */
    private void enregistrer(String fichier) throws IOException, ClassNotFoundException {
        Pattern pattern = Pattern.compile("^.*\\.dat$");
        Matcher matcher = pattern.matcher(fichier);
        boolean matchFound = matcher.find();

        if (!matchFound) {
            JOptionPane.showMessageDialog(null, "Le fichier n'a pas le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
        } 
        else 
        {
            try {
                FileOutputStream enStream = new FileOutputStream(fichier);
                ObjectOutputStream floOutputStream = new ObjectOutputStream(enStream);
                floOutputStream.writeObject(modele);
                floOutputStream.close();
                enStream.close();
            }catch (IOException e4){
                e4.printStackTrace();
            }
        }
    }
    

    /**
     * Ouvre un fichier existant.
     *
     * @param file le fichier à ouvrir
     * @throws IOException            si une erreur d'entrée/sortie se produit
     * @throws ClassNotFoundException si une classe ne peut pas être trouvée
     * @author Naherry MOHAMED DAROUECHE
     */
    private void ouvrirFichier(File file) throws IOException, ClassNotFoundException {
        if (file.exists()) {
            Pattern pattern = Pattern.compile("^.*\\.dat$");
            Matcher matcher = pattern.matcher(file.getAbsolutePath());
            boolean matchFound = matcher.find();

            if (!matchFound) {
                JOptionPane.showMessageDialog(null, "Le fichier n'a pas le bon format", "erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    FileInputStream fichier = new FileInputStream(file);
                    ObjectInputStream flotObjet = new ObjectInputStream(fichier);
                    Object lue = flotObjet.readObject();
                    flotObjet.close();
                    fichier.close();
                    if (lue instanceof ArrayList<?>) {
                        ArrayList<?>arrayList = ((ArrayList<?>)lue);
                            
                        if (arrayList.get(1) instanceof Epreuve) {
                            ArrayList<Epreuve> arrayListEpreuves = ((ArrayList<Epreuve>)arrayList);
                            VueEpreuve vue = new VueEpreuve(arrayListEpreuves);
                            vue.afficherFenetre();
                            fenetre.dispose();
                        }
                        else if (arrayList.get(0) instanceof Equipe) {
                            ArrayList<Equipe> arrayListEpreuves = ((ArrayList<Equipe>)arrayList);
                            VueEquipe vue = new VueEquipe(arrayListEpreuves);
                            vue.afficherFenetre();;
                            fenetre.dispose();
                        }
                        else if (arrayList.get(0) instanceof Session) {
                            ArrayList<Session> sessions = ((ArrayList<Session>)arrayList);  
                            VuePlanning vue = new VuePlanning(new Planning(sessions));
                            vue.afficherFenetre();
                            fenetre.dispose();
                        }   
                    }    
                     
                }
                catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Erreur dans l'ouverture du fichier", "erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Erreur dans l'ouverture du fichier", "erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Le fichier n'existe pas", "erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Constructeur avec modèle et fenêtre.
     *
     * @param modele  le modèle à enregistrer
     * @param fenetre la fenêtre principale
     * @author Naherry MOHAMED DAROUECHE
     */
    public Enregistrer(Object modele, JFrame fenetre) {
        this.modele = modele;
        this.fenetre = fenetre;
    }

    /**
     * Constructeur avec modèle.
     *
     * @param modele le modèle à enregistrer
     * @author Naherry MOHAMED DAROUECHE
     */
    public Enregistrer(Object modele) {
        if (modele instanceof JFrame) {
            this.fenetre = ((JFrame)modele);
        }
        else{
            this.modele = modele;
        }
    }
    

    /**
     * Constructeur par défaut.
     *
     * @author Naherry MOHAMED DAROUECHE
     */
    public Enregistrer() {
        this.modele = null;
        this.fenetre = null;
    }
    
}
