package modele;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexion {
    // Informations de connexion
    private static String URL = "jdbc:mysql://projets.iut-orsay.fr:3306/saes3-mmarti32?useSSL=false&serverTimezone=UTC"; // Remplacez "localhost" si nécessaire
    private static String USER = "saes3-mmarti32"; // Votre identifiant
    private static String PASSWORD = "JPwzfAbGq3EE+Exv"; // Votre mot de passe

    
    public static Connection creerConnexion() {
    	Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("///// Connexion ouverte! /////");
		}
		catch (ClassNotFoundException e){
			System.out.println("il manque le driver oracle");
			System.exit(1);
		}
		catch (SQLException e) {
			try {
				URL = "jdbc:mysql://localhost:3306/saes3-mmarti32?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"; // Remplacez "localhost" si nécessaire
				USER = "root"; // Votre identifiant
				PASSWORD = "Djonodo20050207/"; // Votre mot de passe
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("///// Connexion ouverte! /////");
			} catch (SQLException eBis) {
				System.err.println("Erreur de connexion : " + eBis.getMessage());
				System.exit(1);
			} catch (ClassNotFoundException e1) {
				System.out.println("il manque le driver oracle");
				System.exit(1);
			}
		}
    	return connection;
    }
    
	public static ResultSet exec1Requete (Connection co, String requete, Object... args){
    	PreparedStatement ps=null;
		try {
			ps = co.prepareStatement(requete);
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		
		for (int i=0; i<args.length;i++) {
			if (args[i] instanceof Integer) {
				try {
					ps.setInt(i+1, (Integer) args[i]);
				} catch (SQLException e) {
					System.err.println("Erreur d'exécution : " + e.getMessage());
				}
			}
			else {
				try {
					ps.setString(i+1, (String) args[i]);
				} catch (SQLException e) {
					System.err.println("Erreur d'exécution : " + e.getMessage());
				}
			}
		}
		
		ResultSet res=null;
		try {
			res = ps.executeQuery();
		} catch (SQLException e) {
			System.err.println("Erreur d'exécution : " + e.getMessage());
		}
		return res;
	}

	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("///// Connexion fermée! /////");
		}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}
	}

}