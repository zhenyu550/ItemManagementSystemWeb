package servlet.dad.com;

import java.io.IOException;
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
 * Servlet implementation class ViewItemListServlet
 */
@WebServlet("/ViewItemListServlet")
public class ViewItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewItemListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Create a Item Service
		ItemService itemService = new ItemService();
		
		// Create a list to hold the items
		List<Item> items = new ArrayList<Item>();
		
		try {
			// Select All Items from the database
			items = itemService.selectItemsAll();
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		// Use request to return object to JSP
		request.setAttribute("items", items);
		
		// Forward object to view item page(server).jsp
		request.getRequestDispatcher("ViewItemList.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
