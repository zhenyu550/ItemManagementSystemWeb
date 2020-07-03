package servlet.dad.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.dad.com.Item;
import item.dad.com.ItemService;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemServlet() {
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

		// Create an Item Service
		ItemService itemService = new ItemService();
					
		// Create an Item to hold item data
		Item item = new Item();
		 		
		// Get all data from AddItemForm.jsp
		String itemName = request.getParameter("item_name");
		String itemCode = request.getParameter("item_code");
		String itemPrice = request.getParameter("item_price");
		String itemStockAmount = request.getParameter("item_stock");
		String itemDescription = request.getParameter("item_desc");
				
		
		// Set Item Data
		item.setName(itemName);
		item.setCode(itemCode);
		item.setDescription(itemDescription);
		
		try {
			// Parse into double and set item data
			item.setPrice(Double.parseDouble(itemPrice));
		
			// Parse into integer and set item data
			item.setStockAmount(Integer.parseInt(itemStockAmount));
		
			// Check is name, code, price and stock amount null or whitespace, show error if yes
			// Check is price and stock amount larger than 0, show error if no
			if(itemService.isNullOrWhiteSpace(itemName) || itemService.isNullOrWhiteSpace(itemCode) || 
					itemService.isNullOrWhiteSpace(itemPrice) || itemService.isNullOrWhiteSpace(itemStockAmount)) {
				
				// Show error message 
				out.println("<h3>Error: Invalid Item Data</h3>");
				out.println("<p>Please ensure the following:</br>"
						+ "1. All fields are filled with data (Description is optional).</br>"
						+ "2. Only numerical input for Item Price and Item Stock Amount.</br></p>");
				
				return;
			}

			// Check if the item already exist in the database, show error if yes
			if(itemService.isDuplicate(item)) {
				// Show error message 
				out.println("<h3>Error: Duplicate Item Data</h3>");
				out.println("<p>The item name or item code already existed in the database.</br>"
						+ "Please change the name or code of the item to be added.</br></p>");
				
				return;
			}
				
			// Call InsertItem() of ItemService to insert item into database
			itemService.insertItem(item);
			
			// Return to View Item List
			RequestDispatcher view = request.getRequestDispatcher("ViewItemListServlet");
			view.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// Show error message 
			out.println("<h3>Error: Invalid Item Data</h3>");
			out.println("<p>Please ensure the following:</br>"
					+ "1. All fields are filled with data (Description is optional).</br>"
					+ "2. Only numerical input for Item Price and Item Stock Amount.</br>"
					+ "3. Value of Item Price and Item Stock Amount must be larger than 0.</br></p>");
			
			out.println("<h4>For debugging purpose:</h4>");
			out.println("<p>" + e.toString() + "<p>");
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
