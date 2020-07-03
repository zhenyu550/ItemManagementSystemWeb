/**
 * 
 */
package ordereditem.dad.com;

import db.dad.com.DBConnector;
import item.dad.com.Item;
import item.dad.com.ItemService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * The business service for OrderedItem
 * 
 * @author User
 *
 */
public class OrderedItemService {

	// Private Attributes
	private DBConnector dbConnection = new DBConnector();	// The connection to MySql Database

	// Default Constructor for OrderedItem Service
	public OrderedItemService() {}
	
	// Method to SELECT ALL OrderedItems for an Order using the order Id
	public List<OrderedItem> selectOrderedItems(int orderId) throws Exception {
		
		// Define the order condition
		String orderCondition = "order_id = " + orderId;
		
		// Define the SQL Statement
		String sql = "SELECT * FROM Ordered_Item WHERE %s ORDER BY item_id ASC;";
		
        // Format the SQL Statement with method parameter
		sql = String.format(sql, orderCondition);

		// Define the objects to manipulate the database record
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Create an object List to hold the data of products
		List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		
		try {
			// Establish Connection
			conn = dbConnection.getConnection();
			
			// Execute SQL Statement
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// Warp the result in a list of OrderedItems
			while (rs.next()) {
				
				// Warp each record in the object
				OrderedItem orderedItem = new OrderedItem();
				
				// Get the Item Id
				int itemId = rs.getInt("item_id");
				
				// Define the item condition
				String itemCondition = "id = " + itemId;
				
				// Search and select data of the Item using the item condition
				ItemService itemService = new ItemService();
				List<Item> items = itemService.selectItemsWhere(itemCondition);
				
				// Set the Item of OrderedItem using the first result
				orderedItem.setItem(items.get(0));
				
				// Set the quantity of the OrderedItem
				orderedItem.setQuantity(rs.getInt("quantity"));
				
				// Add orderedItem into list of OrderedItems
				orderedItems.add(orderedItem);
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
		return orderedItems;
	}

	// Method to INSERT list of OrderedItems into the database using List of OrderedItem and Order id
	public int insertOrderedItems(List<OrderedItem> orderedItems, int orderId) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "INSERT INTO Ordered_Item (order_id, item_id, quantity) values (?,?,?)";
				
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			for(OrderedItem orderedItem : orderedItems)
			{
				// Set the data values for each orderedItem in orderedItems
				ps.setInt(1, orderId); 
				ps.setInt(2, orderedItem.getItem().getId()); 
				ps.setInt(3, orderedItem.getQuantity());
	
				// Execute the SQL statement and get number of rows affected
				affectedRows = ps.executeUpdate();
			}
				
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
	
	// Method to UPDATE OrderedItems for an Order using the orderId and new orderedItems data
	public int updateOrderedItems(List<OrderedItem> orderedItems, int orderId) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "UPDATE Ordered_Item item_id=?, quantity=? WHERE order_id=?";
				
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			for(OrderedItem orderedItem : orderedItems)
			{
				// Set the data values for each orderedItem in orderedItems
				ps.setInt(1, orderedItem.getItem().getId()); 
				ps.setInt(2, orderedItem.getQuantity());
				ps.setInt(3, orderId);
	
				// Execute the SQL statement and get number of rows affected
				affectedRows = ps.executeUpdate();
			}
				
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

	// Method to DELETE ALL OrderedItems for an Order using OrderId
	public int deleteOrderedItems(int orderId) throws Exception {
		
		// Declare number of affected rows as -1
		int affectedRows = -1;
		
		// Define the objects to manipulate the database record
		Connection conn = null;
		PreparedStatement ps = null;

		// Define SQL statement
		String sql = "DELETE FROM Ordered_Item WHERE order_id=?";
				
		try {
			// Get the database connection		
			conn = dbConnection.getConnection();
		
			// Prepare the SQL statement
			ps = conn.prepareStatement(sql);

			// Set the data values for each orderedItem in orderedItems
			ps.setInt(1, orderId); 
	
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
