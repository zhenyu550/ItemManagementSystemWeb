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

import order.dad.com.Order;
import order.dad.com.OrderService;

/**
 * Servlet implementation class ViewOrderListServlet
 */
@WebServlet("/ViewOrderListServlet")
public class ViewOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrderListServlet() {
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

		// Create an Order list
		List<Order> orders = new ArrayList<Order>();
		
		// Create an OrderService
		OrderService orderService = new OrderService();
		
		try {
			// Get all processing orders from the database
			orders = orderService.selectOrderWhere("status='Processing'");
			
			// Use request to return object to JSP
			request.setAttribute("orders", orders);
			
			// Forward object to new order item list.jsp
			request.getRequestDispatcher("ViewOrderList.jsp").forward(request, response);

			
		} catch (Exception e) {
			
			e.printStackTrace();
			out.println("<h3>Error: Fail to load Order list from database</h3>");
			out.println("<p>Fail to get order list form the server database</p></br>");
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
