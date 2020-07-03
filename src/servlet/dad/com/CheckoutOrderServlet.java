package servlet.dad.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.dad.com.Item;
import item.dad.com.ItemCart;
import item.dad.com.ItemCartService;
import item.dad.com.ItemService;
import order.dad.com.Order;
import order.dad.com.OrderService;
import ordereditem.dad.com.OrderedItem;
import ordereditem.dad.com.OrderedItemService;

/**
 * Servlet implementation class CheckoutOrderServlet
 */
@WebServlet("/CheckoutOrderServlet")
public class CheckoutOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Setting up the content type of web page
		response.setContentType("text/html");
		
		// Write message to the web page if error occurs
		PrintWriter out = response.getWriter();

		/* 
		 * Create required services or objects
		 */
		// Item
		List<Item> items = new ArrayList<Item>();
		ItemService itemService = new ItemService();
		
		// ItemCart
		List<ItemCart> itemCarts = new ArrayList<ItemCart>();
		ItemCartService itemCartService = new ItemCartService();
		
		// Order
		Order order = new Order();
		OrderService orderService = new OrderService();
		
		// OrderedItem
		ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		OrderedItemService orderedItemService = new OrderedItemService();
		
		/*
		 * Insert the Order into database
		 */
		try {
			// Create total price variable
			double totalPrice = 0;
			
			// Get the ordered items list using the item cart
			itemCarts = itemCartService.selectItemCartsAll();
			for(ItemCart itemCart : itemCarts) {
				// Get Item data for each item in the cart
				items = itemService.selectItemsWhere("id=" + itemCart.getItemId());

				// Set the OrderedItem
				OrderedItem orderedItem = new OrderedItem();
				orderedItem.setItem(items.get(0));
				orderedItem.setQuantity(itemCart.getQuantity());
				
				// Sum up the total price
				totalPrice += orderedItem.getSubTotal();
				
				// Add the Item 
				
				// Add the orderedItem into orderedItems
				orderedItems.add(orderedItem);
			}
			
			// Get the time and date the order was placed
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			// Generate the order code using the current date time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
			String code = currentDateTime.format(formatter);

			// Set Order Data
			order.setCode(code);
			order.setDateTime(currentDateTime);
			order.setTotalPrice(totalPrice);
			order.setOrderedItems(orderedItems);
			
			// Insert the Order into database
			orderService.insertOrder(order);

			// Get the order id of the order (as order id is auto incremented in database)
			List<Order> selectedOrders = orderService.selectOrderWhere("code=" + code);
			
			// Insert the OrderedItem list of the order into the database
			orderedItemService.insertOrderedItems(orderedItems, selectedOrders.get(0).getId());
			
			// Update the stock amount of the items
			for(OrderedItem orderedItem : orderedItems) {
				// Get the item data
				Item updatedItem = orderedItem.getItem();
			
				// Set the new stock amount for that item
				int newStockAmount = updatedItem.getStockAmount() - orderedItem.getQuantity();
				updatedItem.setStockAmount(newStockAmount);
				
				// Update the stock amount of the item
				itemService.updateItem(updatedItem);
			}
			
			// Return to View Item List for new Order
			RequestDispatcher view = request.getRequestDispatcher("ViewOrderItemListServlet");
			view.forward(request, response);

			
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h1>Error: Fail to Insert Order</h1>");
			out.println("<h3>For debugging purpose:</h3>");
			out.println("<p>" + e.toString() + "</p>");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
