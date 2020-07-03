<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "item.dad.com.Item" %>
<%@ page import = "item.dad.com.ItemService" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>
  
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Order Item</title>
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
    <h1>Item List</h1>
    <div class="container">
        <form method="post" action="">
            <table class="table">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Item Price (RM)</th>
                        <th>Item Stock</th>
                        <th>Item Description</th>
                        <th>Select</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <%
						// Get the item list from database using Servlet
						List<Item> items = (ArrayList<Item>) request.getAttribute("items");
						for(Item item : items)
						{
					%>
                    <tr>
                        <td><%=item.getName() %></td>
                        <td><%=item.getPrice() %></td>
                        <td><%=item.getStockAmount() %></td>
                        <td><%=item.getDescription() %></td>
                    <%
                    		if(item.getStockAmount() > 0)
                    		{
                    %>
                        <%--Item Id is assigned as check box value for data retrieval purposes--%>
                        	<td><input type="checkbox" name="checkboxList" id="checkbox_<%=item.getId()%>" value="<%=item.getId() %>"
                        			onchange="document.getElementById('item_quantity_<%=item.getId() %>').disabled = !this.checked;"></td>
                        	<td><input type="number" class="form-control" name="item_quantity" id="item_quantity_<%=item.getId() %>" 
                        			min="1" max="<%=item.getStockAmount() %>" disabled></td>
                    <%
                    		} else {
                    %>
                            <td><input type="checkbox" name="checkboxList" id="checkbox_<%=item.getId()%>" value="<%=item.getId() %>"
                                	onchange="document.getElementById('item_quantity_<%=item.getId() %>').disabled = !this.checked;"
                                	disabled></td>
                            <td><input type="number" class="form-control" name="item_quantity" id="item_quantity_<%=item.getId() %>" 
                                	min="0" max="0" disabled></td>

                       
                        <%--
                        	Unique ID is assigned to each item_quantity numeric input for event handling
                        	
                        	The JavaScript function
                        	document.getElementById('item_quantity_itemId) determines which input field to be disabled / enabled by using the item id 
                         --%>
                    </tr>
                    <%
							}
						}
					%>
                </tbody>
            </table>
            
            <%--Buttons Section--%>            
			<button type="submit" class="btn btn-primary" name ="action" value="cart" formaction="AddItemToCartServlet">Proceed To Item Cart</button>
			
        </form>
    </div>    
</body>

</html>