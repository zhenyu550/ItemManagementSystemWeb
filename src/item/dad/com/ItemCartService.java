/**
 * 
 */
package item.dad.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.dad.com.DBConnector;

/**
 * The business class of Item Cart 
 * 
 * @author User
 *
 */
public class ItemCartService {

	// Private Attributes
	private DBConnector dbConnection = new DBConnector();	// The connection to MySql Database
	
	// Default Constructor for Item Cart Service
	public ItemCartService() {}
	
	// Method to SELECT ALL Items in the Item Cart
	public List<ItemCart> selectItemCartsAll() throws Exception {
		
		// Define the SQL Statement
		String sql = "SELECT * FROM ItemCart ORDER BY item_id ASC;";
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Create an object List to hold the data of products
		List<ItemCart> itemCarts = new ArrayList<ItemCart>();
		
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Execute SQL Statement
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// Warp the result in a list of Items
			while (rs.next()) {
				
				// Warp each record in the object
				ItemCart itemCart = new ItemCart();
				itemCart.setItemId(rs.getInt("item_id"));
				itemCart.setQuantity(rs.getInt("quantity"));
				
				// Add item cart into list of item carts
				itemCarts.add(itemCart);
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
		
		// Return the list of items
		return itemCarts;
	}

	// Method to INSERT an Item into the Item Cart
	public int insertItemCart(ItemCart itemCart) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "INSERT INTO ItemCart (item_id, quantity) values (?,?)";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemCart.getItemId()); 
			ps.setInt(2, itemCart.getQuantity()); 
		
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
	
	// Method to UPDATE an Item quantity in the Item Cart
	public int updateItemCart(ItemCart itemCart) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "UPDATE ItemCart SET quantity=? WHERE item_id=?";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setInt(1, itemCart.getQuantity()); 
			ps.setInt(2, itemCart.getItemId()); 
		
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
	
	// Method to DELETE an Item from the Item Cart
	public int deleteItemCart(ItemCart itemCart) throws Exception {
		// Declare number of affected rows as -1
				int affectedRows = -1;
				
				// Define the objects to manipulate the database record
				Connection conn = null;
				PreparedStatement ps = null;

				// Define SQL statement
				String sql = "DELETE FROM ItemCart WHERE item_id=?";
				
				try {
					// Get the database connection		
					conn = dbConnection.getConnection();
				
					// Prepare the SQL statement and set the data values
					ps = conn.prepareStatement(sql);
					ps.setInt(1, itemCart.getItemId()); 
					
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
	
	// Method to DELETE ALL Items in the Item Cart
	public int deleteItemCartsAll() throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "DELETE FROM ItemCart";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			
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
