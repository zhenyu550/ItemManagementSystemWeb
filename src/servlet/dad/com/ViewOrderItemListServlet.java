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
import item.dad.com.ItemService;

/**
 * Servlet implementation class ViewOrderItemListServlet
 */
@WebServlet("/ViewOrderItemListServlet")
public class ViewOrderItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrderItemListServlet() {
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

		// Create a Item Service
		ItemService itemService = new ItemService();
		
		// Create a list to hold the items
		List<Item> items = new ArrayList<Item>();
		
		try {
			// Select All Items from the database
			items = itemService.selectItemsAll();
			
			// Use request to return object to JSP
			request.setAttribute("items", items);
			
			// Forward object to new order item list.jsp
			request.getRequestDispatcher("NewOrderItemList.jsp").forward(request, response);

			
		} catch(Exception e) {
			
			e.printStackTrace();
			out.println("<h3>Error: Fail to load Item list from database</h3>");
			out.println("<p>Fail to get item list form the server database</p></br>");
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
