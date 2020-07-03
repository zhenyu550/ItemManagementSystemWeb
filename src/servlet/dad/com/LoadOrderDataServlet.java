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
import ordereditem.dad.com.OrderedItem;
import ordereditem.dad.com.OrderedItemService;

/**
 * Servlet implementation class LoadOrderDataServlet
 */
@WebServlet("/LoadOrderDataServlet")
public class LoadOrderDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadOrderDataServlet() {
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
		
		// OrderedItem
		List<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
		OrderedItemService orderedItemService = new OrderedItemService();
		
		/*
		 * Get all required data from database using the order id
		 */
		// Get the selected orderId
		String[] orderIds = request.getParameterValues("radioButton");
		
		// Show Error Message if no Order selected
		if(orderIds == null) {
			out.println("<h3>Error: No Order selected.</h3>");
			out.println("<p>There is no order selected. </br>Please go back and select an order to be processed.</p></br>");

			return;
		}

		try {
			// Get Order Data from database using the order Id
			List<Order> selectedOrder = orderService.selectOrderWhere("id=" + orderIds[0]);
			order = selectedOrder.get(0);
			
			// Get Ordered Item Data from database using the order Id
			orderedItems = orderedItemService.selectOrderedItems(Integer.parseInt(orderIds[0]));
			
			// Assign the ordered items data into order
			order.setOrderedItems(orderedItems);
			
			// Use request to return object to JSP
			request.setAttribute("order", order);
			
			// Forward object to Edit Item Form
			request.getRequestDispatcher("ProcessOrderForm.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			out.println("<h3>Error: Unable to access database</h3>");
			out.println("<p>Fail to get order data form the server database</p></br>");
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
