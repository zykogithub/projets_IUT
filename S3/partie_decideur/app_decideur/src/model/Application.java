package model;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.mindrot.jbcrypt.BCrypt;

import controller.ControleurConnexion;
import view.VueConnexion;

public class Application {
	public static Connection co;
	public static JFrame frame;
	public static int id_internaute;
	public static ArrayList<Groupe> groupes;
	public static int groupeActif;
	public static JPanel container;
	public static JPanel mainContainer;
	public static CardLayout cardLayout;
	
	public static Groupe getGroupById(int id) {
	    for (Groupe groupe : groupes) {
	        if (groupe.getId() == id) {
	            return groupe;
	        }
	    }
	    return null;
	}

	
	public static int checkInfos(String courriel, String mdp) {
		int result = -1;
		String requete = "SELECT hashageMDP\r\n"
        		+ "FROM internaute\r\n"
        		+ "WHERE courriel=?";
        ResultSet res = Connexion.exec1Requete(Application.co,requete,courriel);
		try {
			String hashString = "";
			while (res.next()) {
				hashString=res.getString(1);
			}
			if(hashString=="") {
				return result;
			}
			String hashStringPourVerif = hashString.replaceFirst("\\$2y\\$", "\\$2a\\$");
			if (BCrypt.checkpw(mdp,hashStringPourVerif)) {
				requete = "SELECT id_internaute\r\n"
        		+ "FROM internaute\r\n"
        		+ "WHERE courriel=? AND hashageMDP=?";
				res = Connexion.exec1Requete(Application.co,requete,courriel,hashString);
				while (res.next()) {
					result=res.getInt(1);
				}
				return result;
			} else {
				System.err.println("Le mot de passe est invalide.");
			}
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		return result;
	}
	
	public static ArrayList<Groupe> listeGroupe(int id){
		ArrayList<Groupe> groupes = new ArrayList<>();
		String requete = """ 
		SELECT *
        FROM infos_membre IFO
        INNER JOIN role R ON IFO.id_role = R.id_role
        WHERE R.id_role=3 AND id_internaute=?""";
				
				
        ResultSet res = Connexion.exec1Requete(Application.co,requete,id);
		try {
			while (res.next()) {
				groupes.add(new Groupe(res.getInt(1)));
			}
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		return groupes;
	}
	
    private static void onWindowClose() {
    	Connexion.closeConnection(co);
        System.exit(0);
    }
    
	public static void run() {
        frame = new JFrame("Application de Connexion");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClose();
            }
        });
        
        VueConnexion vue = new VueConnexion();

        // Centrer les éléments verticalement et horizontalement
		cardLayout = new CardLayout();
        container = new JPanel(new GridBagLayout());
		mainContainer = new JPanel(cardLayout);
        container.add(vue);
		mainContainer.add(container,"connexion");
        frame.add(mainContainer);

        new ControleurConnexion(vue,frame);

        // Mettre la fenêtre en plein écran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
	}
	
    public static void main(String[] args) {
		co = Connexion.creerConnexion();
    	run();
    }
	public static ImageIcon createScaledIcon(String path, int width, int height) {
		ImageIcon icon = new ImageIcon(Application.class.getClassLoader().getResource(path));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}