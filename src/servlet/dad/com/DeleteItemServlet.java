package servlet.dad.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.dad.com.Item;
import item.dad.com.ItemService;

/**
 * Servlet implementation class DeleteItemServlet
 */
@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItemServlet() {
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

		// Get the selected itemId
		String[] itemIds = request.getParameterValues("radioButton");

		if(itemIds == null) {
			out.println("<h3>Error: No item selected.</h3>");
			out.println("<p>There is no item selected. </br>Please go back and select an item to be editted.</p></br>");

			return;
		}
		
		// show the alert message 
		
		// Get the item data using the item id
		List<Item> selectedItems;
		try {
			selectedItems = itemService.selectItemsWhere("id = " + itemIds[0]);
			item = selectedItems.get(0);

			// Delete the item from the database
			itemService.deleteItem(item);
			
			// Return to View Item List
			RequestDispatcher view = request.getRequestDispatcher("ViewItemListServlet");
			view.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			out.println("<h3>Error: Unable to access database</h3>");
			out.println("<p>Fail to get item list form the server database</p></br>");
			out.println("<h4>For debugging purpose:</h4>");
			out.println("<p>" + e.toString() + "<p>");
			
			return;
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
