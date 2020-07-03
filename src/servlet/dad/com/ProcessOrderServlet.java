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

import order.dad.com.Order;
import order.dad.com.OrderService;

/**
 * Servlet implementation class ProcessOrderServlet
 */
@WebServlet("/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessOrderServlet() {
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
		 * Create all required services and objects
		 */
		// Order
		Order order = new Order();
		OrderService orderService = new OrderService();
		
		try {
			
			// Get the order Id of the order from the Process Order Form
			String orderId = request.getParameter("order_id");
			
			// Get the Order Data from the database
			List<Order> selectedOrders = orderService.selectOrderWhere("id=" + orderId);
			order = selectedOrders.get(0);
			
			// Set the status of the order to "Completed"
			order.setStatus("Completed");
			
			// Update the order with new status
			orderService.updateOrder(order);
			
			// Return to View Order List
			RequestDispatcher view = request.getRequestDispatcher("ViewOrderListServlet");
			view.forward(request, response);


		} catch (Exception e) {
			
			e.printStackTrace();
			out.println("<h3>Error: Unable to process order!</h3>");
			out.println("<p>An error occured and hence the order cannot be processed.</p>");
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
