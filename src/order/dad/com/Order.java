/**
 * 
 */
package order.dad.com;

import ordereditem.dad.com.OrderedItem;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The entity class of Item that stores the details of the Order
 * 
 * @author User
 *
 */

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//	Private attributes
	private int id;
	private String code;
	private LocalDateTime dateTime;
	private String status;
	private double totalPrice;
	private List<OrderedItem> orderedItems;
	
	//	Getter
	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getStatus() {
		return status;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public List<OrderedItem> getOrderedItems(){
		return orderedItems;
	}
	
	//	Setters
	public void setId(int id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setOrderedItems(List<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}
}
