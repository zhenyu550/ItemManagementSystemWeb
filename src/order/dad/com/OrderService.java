/**
 * 
 */
package order.dad.com;

import db.dad.com.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Business Service Class for Order
 * 
 * @author User
 *
 */
public class OrderService {

	// Private Attributes
	private DBConnector dbConnection = new DBConnector();	// The connection to MySql Database

	// Default Constructor for Order Service
	public OrderService() {}
	
	// Method to SELECT ALL Orders in the database
	public List<Order> selectOrderAll() throws Exception{
		
		// Define the SQL Statement
		String sql = "SELECT * FROM Orders ORDER BY id ASC;";
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Create an object List to hold the data of orders
		List<Order> orders = new ArrayList<Order>();
		
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Execute SQL Statement
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// Warp the result in a list of Orders
			while (rs.next()) {
				
				// Warp each record in the object
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setCode(rs.getString("code"));
				order.setTotalPrice(rs.getDouble("total_price"));
				
				// Parse the Date Time from MySql Database to LocalDateTime format in Java
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	        	order.setDateTime(LocalDateTime.parse(rs.getString("date_time"), formatter));

				order.setStatus(rs.getString("status"));
				
				// Add item into list of items
				orders.add(order);
			}
			
		} catch (Exception exception){
			
			throw exception;
			
		} finally {
			
			// Close all database related objects
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
			
			if (conn != null)
				conn.close();
		}
		
		// Return the list of orders
		return orders;
	}

	// Method to SELECT Orders WHERE Conditions are fulfilled
	public List<Order> selectOrderWhere(String condition) throws Exception{
		
		// Define the SQL Statement
        String sql = "SELECT * FROM Orders WHERE %s ORDER BY id ASC;";
        
        // Format the SQL Statement with method parameter
		sql = String.format(sql, condition);
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Create an object List to hold the data of orders
		List<Order> orders = new ArrayList<Order>();
		
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Execute SQL Statement
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// Warp the result in a list of Orders
			while (rs.next()) {
				
				// Warp each record in the object
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setCode(rs.getString("code"));
				order.setTotalPrice(rs.getDouble("total_price"));
				
				// Parse the Date Time from MySql Database to LocalDateTime format in Java
	    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
	        	order.setDateTime(LocalDateTime.parse(rs.getString("date_time"), formatter));

				order.setStatus(rs.getString("status"));
				
				// Add item into list of items
				orders.add(order);
			}
			
		} catch (Exception exception){
			
			throw exception;
			
		} finally {
			
			// Close all database related objects
			if (rs != null)
				rs.close();
			
			if (stmt != null)
				stmt.close();
			
			if (conn != null)
				conn.close();
		}
		
		// Return the list of orders
		return orders;
	}

	// Method to INSERT an Order into the database
	public int insertOrder(Order order) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "INSERT INTO Orders (code, total_price, date_time) values (?,?,?);";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getCode()); 
			ps.setDouble(2, order.getTotalPrice());
		
			// Format the string from LocalDateTime format to MySql Database default format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			ps.setString(3, order.getDateTime().format(formatter));

			// Execute the SQL statement and get number of rows affected
			affectedRows = ps.executeUpdate();
				
		} catch (Exception exception) {
			
			throw exception;
			
		} finally {
			
			// Close all database related objects
			if (ps != null)
				ps.close();
						
			if (conn != null)
				conn.close();
		}
		
		// Return number of rows affected
		return affectedRows;

	}

	// Method to UPDATE data of an Order using new order data
	public int updateOrder(Order order) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "UPDATE Orders SET code=?, total_price=?, date_time=?, status=? WHERE id=?;";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getCode()); 
			ps.setDouble(2, order.getTotalPrice());
		
			// Format the string from LocalDateTime format to MySql Database default format
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			ps.setString(3, order.getDateTime().format(formatter));

			ps.setString(4, order.getStatus());
			ps.setInt(5, order.getId());
			
			// Execute the SQL statement and get number of rows affected
			affectedRows = ps.executeUpdate();
				
		} catch (Exception exception) {
			
			throw exception;
			
		} finally {
			
			// Close all database related objects
			if (ps != null)
				ps.close();
						
			if (conn != null)
				conn.close();
		}
		
		// Return number of rows affected
		return affectedRows;

	}

	// Method to DELETE an Order from the database
	public int deleteOrder(Order order) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "DELETE FROM Orders WHERE id=?;";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setInt(1, order.getId());
			
			// Execute the SQL statement and get number of rows affected
			affectedRows = ps.executeUpdate();
				
		} catch (Exception exception) {
			
			throw exception;
			
		} finally {
			
			// Close all database related objects
			if (ps != null)
				ps.close();
						
			if (conn != null)
				conn.close();
		}
		
		// Return number of rows affected
		return affectedRows;

	}

}
