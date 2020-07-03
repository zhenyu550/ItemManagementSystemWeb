package servlet.dad.com;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class AddItemToCartServlet
 */
@WebServlet("/AddItemToCartServlet")
public class AddItemToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemToCartServlet() {
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
		ItemCartService itemCartService = new ItemCartService();
		
		// Get the item Id and amount
		String[] selectedItemsId = request.getParameterValues("checkboxList");
		String[] itemQuantities = request.getParameterValues("item_quantity");
		
		try {
			// Clear the item cart if there are items inside
			List<ItemCart> existingItems = new ArrayList<ItemCart>();
			existingItems = itemCartService.selectItemCartsAll();
			if(!existingItems.isEmpty()) {
				itemCartService.deleteItemCartsAll();
			}
			
			// Insert the item Id and quantity into database (ItemCart Entity)
			for(int index = 0; index < selectedItemsId.length; index++) {
				// Set the itemCart
				ItemCart itemCart = new ItemCart();	
				itemCart.setItemId(Integer.parseInt(selectedItemsId[index]));
				itemCart.setQuantity(Integer.parseInt(itemQuantities[index]));
				
				// Insert into database
				itemCartService.insertItemCart(itemCart);
			}
			
			// Go to Item Cart
			RequestDispatcher view = request.getRequestDispatcher("LoadItemCartServlet");
			view.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			out.println("<h1>Error: Fail to Add Item(s) to Cart</h1>");
			out.println("<p>Fail to add item(s) to cart.</br>"
					+ "Please ensure the following: </br>1. Item amount is not empty or less than 1.</p>");
			out.println("<h3>For debugging purposes:</h3>");
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
