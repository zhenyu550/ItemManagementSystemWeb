/**
 * 
 */
package ordereditem.dad.com;

import item.dad.com.Item;

import java.io.Serializable;

/**
 * The entity class of OrderedItem that stores the details of the OrderedItem
 * 
 * @author User
 *
 */
public class OrderedItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//	Private attributes
	private Item item;
	private int quantity;
	
	//	Getters
	public Item getItem() {
		return item;
	}
		
	public int getQuantity() {
		return quantity;
	}
	
	public double getSubTotal() {
		return item.getPrice() * quantity;
	}
	
	//	Setters
	public void setItem(Item item) {
		this.item = item;
	}
		
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
