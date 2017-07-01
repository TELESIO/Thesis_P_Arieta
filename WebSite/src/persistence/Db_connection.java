package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Db_connection {

	private String dbURI;
	private String username;
	private String password;

	public Db_connection(){
		try {
			loadConfigurations();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() 
	{
		Connection connection = null;
			try {
				connection = DriverManager.getConnection(dbURI, username, password);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
				        "Impossible to Establish Connection with Database", "ERROR", JOptionPane.ERROR_MESSAGE, null);
				    }
		return connection;
	}

	private void loadConfigurations() throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		dbURI="jdbc:mysql://localhost:3306/th_db?useSSL=true";
		username="root";
		password="tesi";
		
	}
}

	
	
