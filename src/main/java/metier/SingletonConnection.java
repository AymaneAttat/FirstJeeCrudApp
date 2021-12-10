package metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.IProduitDao;
import dao.ProduitDaoImpl;

public class SingletonConnection {
	private String url;
    private String username;
    private String password;

    SingletonConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
	public static SingletonConnection getInstance() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

		SingletonConnection instance = new SingletonConnection(
                "jdbc:mysql://localhost:3306/projectProd", "root", "");
        return instance;
	}
	public Connection getConnection() throws SQLException {
    	Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion;
    }
	//R�cup�rationDao
    public IProduitDao getIProduitDao() {
        return new ProduitDaoImpl(this);
    }
}
