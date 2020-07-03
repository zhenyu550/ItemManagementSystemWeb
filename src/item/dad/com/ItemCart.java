/**
 * 
 */
package item.dad.com;

import java.io.Serializable;

/**
 * The entity class of Item Cart that temporary holds the Ordered Items of an Order
 *  
 * @author User
 *
 */
public class ItemCart implements Serializable {

	private static final long serialVersionUID = 1L;

	// Private attributes
	private int itemId;
	private int quantity;
	
	// Getters
	public int getItemId() {
		return itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	
	// Setters
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
