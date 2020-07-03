/**
 * 
 */
package item.dad.com;

import db.dad.com.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * The Business Service Class for Item
 * 
 * @author User
 *
 */
public class ItemService {

	// Private Attributes
	private DBConnector dbConnection = new DBConnector();	// The connection to MySql Database
	
	// Default Constructor for Item Service
	public ItemService() {}
	
	// Method to SELECT ALL Items in the database
	public List<Item> selectItemsAll() throws Exception{
		
		// Define the SQL Statement
		String sql = "SELECT * FROM Item ORDER BY id ASC;";
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Create an object List to hold the data of products
		List<Item> items = new ArrayList<Item>();
		
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Execute SQL Statement
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// Warp the result in a list of Items
			while (rs.next()) {
				
				// Warp each record in the object
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setCode(rs.getString("code"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setStockAmount(rs.getInt("stock_amount"));
				item.setDescription(rs.getString("description"));
				
				// Add item into list of items
				items.add(item);
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
		return items;
	}

	// Method to SELECT Items WHERE conditions are fulfilled
	public List<Item> selectItemsWhere(String condition) throws Exception{
		
		// Define the SQL Statement
        String sql = "SELECT * FROM Item WHERE %s ORDER BY id ASC;";
        
        // Format the SQL Statement with method parameter
		sql = String.format(sql, condition);
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Create an object List to hold the data of products
		List<Item> items = new ArrayList<Item>();
		
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Execute SQL Statement
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// Warp the result in a list of Items
			while (rs.next()) {
				
				// Warp each record in the object
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setCode(rs.getString("code"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setStockAmount(rs.getInt("stock_amount"));
				item.setDescription(rs.getString("description"));
				
				// Add item into list of items
				items.add(item);
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
		return items;
	}
	
	// Method to INSERT an Item into the database
	public int insertItem(Item item) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "INSERT INTO Item (code, name, price, stock_amount, description) values (?,?,?,?,?)";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setString(1, item.getCode()); 
			ps.setString(2, item.getName()); 
			ps.setDouble(3, item.getPrice());
			ps.setInt(4, item.getStockAmount());
			ps.setString(5, item.getDescription());
		
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

	// Method to UPDATE an Item data in the database
	public int updateItem(Item item) throws Exception {

		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "UPDATE Item SET code=?, name=?, price=?, stock_amount=?, description=? WHERE id=?";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setString(1, item.getCode()); 
			ps.setString(2, item.getName()); 
			ps.setDouble(3, item.getPrice());
			ps.setInt(4, item.getStockAmount());
			ps.setString(5, item.getDescription());
			ps.setInt(6, item.getId());
		
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
	
	// Method to DELETE an Item from the database
	public int deleteItem(Item item) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "DELETE FROM Item WHERE id=?";
		
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement and set the data values
			ps = conn.prepareStatement(sql);
			ps.setInt(1, item.getId()); 
			
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
	
	// Method to check if a data is duplicated
	public boolean isDuplicate(Item item) throws Exception {

		// Initialize the boolean
		boolean isFound = false;

		// Define the SQL Statement
        String sql = "SELECT EXISTS(SELECT * FROM Item WHERE name=? OR code=?) AS result";
        		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Prepare SQL statement and set attributes to be checked
			ps = conn.prepareStatement(sql);
			ps.setString(1, item.getName()); 
			ps.setString(2, item.getCode()); 

			// Execute SQL Statement
			rs = ps.executeQuery();
			
			// Read the result set for one time
			rs.next();
				
			// Check the result obtained
			if(rs.getInt("result") >= 1) {
				// value 1 indicate that the result for SELECT EXISTS is true
				isFound = true;
			}

		} catch (Exception exception){
			
			throw exception;
			
		} finally {
			
			// Close all database related objects
			if (rs != null)
				rs.close();
			
			if (ps != null)
				ps.close();
			
			if (conn != null)
				conn.close();
		}
		
		// Return the final result
		return isFound;
	}
	
	// Method to check if a string is Null or WhiteSpace
	public boolean isNullOrWhiteSpace(String str) {
		
	    return str == null || isWhitespace(str);
	}
	
	// Private Method to check if a string is WhiteSpace only
	private static boolean isWhitespace(String str) {
		// Get the length of the string
	    int length = str.length();
	    
	    if (length > 0) {
	    	
	    	// Check the string contains any character other than WhiteSpace or not
	        for (int i = 0; i < length; i++) {
	            if (!Character.isWhitespace(str.charAt(i))) {
	            	// Return false if the character checked is not WhiteSpace (String is not empty)
	                return false;
	            }
	        }
	        
	        // Return true if only WhiteSpace is in the string
	        return true;
	    }
	    
	    // Return false if the string is null
	    return false;
	}

}
