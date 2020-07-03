<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "item.dad.com.Item" %>
<%@ page import = "item.dad.com.ItemService" %>
<%@ page import = "ordereditem.dad.com.OrderedItem" %>
<%@ page import = "ordereditem.dad.com.OrderedItemService" %>

<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>View Cart</title>
</head>
<style>
    .col-sm-4 {
        text-align: center;
        size: 20px;
        font-family: Arial;
        padding: 10px 30px 10px 30px;
    }

    .container {}
</style>

<body>
    <h1>Item Cart</h1>
    <div class="container">
        <form method="post" action="CheckoutOrderServlet">
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
                		// Create Total
                		double totalPrice = 0;
                		
						// Get the item list from database using Servlet
						List<OrderedItem> orderedItems = (List<OrderedItem>) request.getAttribute("orderedItems");
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
							totalPrice += orderedItem.getSubTotal();
						}
                    %>
                </tbody>
                
            </table>
            <h3>Total price : RM<%=totalPrice %></h3>
            <p>Please press Checkout button to place the order. You cannot cancel the order once the order was placed.</br>
            Please press Reset button to reset or cancel the order.</br></br>
            To edit the item amount, you need to reset the order and add the items again.</p>
            <button type="submit" class="btn btn-primary">Checkout</button>
			<button type="submit" class="btn btn-primary" name="action" value="back" formaction="ViewOrderItemListServlet">Reset</button>
        </form>
    </div>
</body>

</html>