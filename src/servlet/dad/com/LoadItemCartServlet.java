package servlet.dad.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.dad.com.Item;
import item.dad.com.ItemCart;
import item.dad.com.ItemCartService;
import item.dad.com.ItemService;
import ordereditem.dad.com.OrderedItem;

/**
 * Servlet implementation class LoadItemCartServlet
 */
@WebServlet("/LoadItemCartServlet")
public class LoadItemCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadItemCartServlet() {
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
		
		// Writing message to the web page
		PrintWriter out = response.getWriter();

		// Create required services and objects
		ItemService itemService = new ItemService();
		ItemCartService itemCartService = new ItemCartService();
		
		// Create Ordered Items list to hold the item data and quantity
		List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		
		// Create list to hold ItemCart data
		List<ItemCart> itemCarts = new ArrayList<ItemCart>();
				
		try {
			// Get all items in the Item Cart
			itemCarts = itemCartService.selectItemCartsAll();
			
			for(ItemCart itemCart : itemCarts) {
				// Get Item data for each item in the cart
				List<Item> selectedItems = itemService.selectItemsWhere("id=" + itemCart.getItemId());
				
				// Create OrderedItem and set data
				OrderedItem orderedItem = new OrderedItem();
				orderedItem.setItem(selectedItems.get(0));
				orderedItem.setQuantity(itemCart.getQuantity());
				
				// Add the orderedItem into orderedItems
				orderedItems.add(orderedItem);
			}
			
			// Use request to return object to JSP
			request.setAttribute("orderedItems", orderedItems);
			
			// Forward object to View Cart Form
			request.getRequestDispatcher("ViewCart.jsp").forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h1>Error: Fail to load Item Cart</h1>");
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
