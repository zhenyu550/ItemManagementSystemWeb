<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "order.dad.com.Order" %>
<%@ page import = "order.dad.com.OrderService" %>
<%@ page import = "ordereditem.dad.com.OrderedItem" %>
<%@ page import = "ordereditem.dad.com.OrderedItemService" %>
<%@ page import = "java.time.LocalDateTime" %>
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Process Order</title>
</head>

<body>

    <%						
	// Get the order data from Load Order Data Servlet
	Order order = (Order) request.getAttribute("order");
    
    /*
     * Convert the LocalDateTime format of orderDateTime to a date string and time string
     */
    // Get the order date time
    LocalDateTime orderDateTime = order.getDateTime();
    
    // Set Date and Time format
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Convert the date and time to the specified format
    String date = orderDateTime.format(dateFormat); 
    String time = orderDateTime.format(timeFormat);
    
    // Convert the total price of the order from double to string format
    String totalPrice = "RM " + Double.toString(order.getTotalPrice());
    
    %>

    <h1>Process Order</h1>

    <div class="container">
        <form method="post" action="ProcessOrderServlet">
            <div class="form-group">
                <label for="order_id">Order Id:</label>
                <input type="text" class="form-control" name="order_id" value="<%=order.getId() %>" readonly>
            </div>

            <div class="form-group">
                <label for="order_code">Order Code:</label>
                <input type="text" class="form-control" name="order_code" value="<%=order.getCode() %>" readonly>
            </div>
            <div class="form-group">
                <label for="order_date">Order Date:</label>
                <input type="text" class="form-control" name="order_date" value="<%=date %>" readonly>
            </div>
            <div class="form-group">
                <label for="order_time">Order Time:</label>
                <input type="text" class="form-control" name="order_time" value="<%=time %>" readonly>
            </div>
            <div class="form-group">
                <label for="total_price">Total Price:</label>
                <input type="text" class="form-control" name="total_price" value="<%=totalPrice %>" readonly>
            </div>
            <div class="form-group">
                <label for="ordered_items">Ordered Items:</label>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Price (RM)</th>
                            <th>Quantity</th>
                            <th>Sub Total (RM)</th>
                        </tr>
                    </thead>

                    <tbody>
                        <%
						// Get the item list from database using Servlet
						List<OrderedItem> orderedItems = order.getOrderedItems();
						for(OrderedItem orderedItem : orderedItems)
						{
					%>
                        <tr>
                            <td><%=orderedItem.getItem().getName() %></td>
                            <td><%=orderedItem.getItem().getPrice() %></td>
                            <td><%=orderedItem.getQuantity() %></td>
                            <td><%=orderedItem.getSubTotal() %></td>
                        </tr>
                        <%
						}
                    %>
                    </tbody>

                </table>

            </div>

			</br>
            <button type="submit" class="btn btn-primary" name="action" value="complete">Complete Order</button>
            <button type="submit" class="btn btn-primary" formaction="ViewOrderListServlet">Back</button>
        </form>
    </div>

</body>

</html>