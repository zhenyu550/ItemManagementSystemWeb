/**
 * 
 */
package db.dad.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ChinZhenYu
 *
 */
public class DBConnector {

	
	/**
	 * This is the default constructor
	 */
	public DBConnector() {}
	
	/**
	 * This method that loads the database drive and create the database connection object
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		// The connection string format is "jdbc:mysql://host::port/database, user, password"
		Connection connection = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/terminaldb?useSSL=false", "root", "test");
		
		return connection;
	}
	
	// Method to test the database connection (for debugging purposes)
	public static void main(String[] args) {
		try {
			DBConnector dbConnector = new DBConnector();
			dbConnector.getConnection();
			
			System.out.println("Database Connected!");
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
