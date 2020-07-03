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
    <title>Item List</title>
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
        <form method="post" action="LoadItemDataServlet">
            <table class="table">
                <thead>
                    <tr>
                        <th>Item ID</th>
                        <th>Item Name</th>
                        <th>Item Price (RM)</th>
                        <th>Item Stock</th>
                        <th>Item Description</th>
                        <th>Select</th>
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
                        <td><%=item.getId() %></td>
                        <td><%=item.getName() %></td>
                        <td><%=item.getPrice() %></td>
                        <td><%=item.getStockAmount() %></td>
                        <td><%=item.getDescription() %></td>
                        <%--Item Id is assigned as radio button value for data retrieval purposes--%>
                        <td><input type="radio" name="radioButton" value="<%=item.getId()%>"></td>
                    </tr>
                    <%
						}								
					%>
                </tbody>
            </table>
            
            <%--Buttons Section--%>
            <button type="submit" class="btn btn-primary" name="action" value="add"formaction="AddItemForm.jsp">Add Item</button>
            <button type="submit" class="btn btn-primary" name="action" value="edit">Edit Item</button>
			<button type="submit" class="btn btn-primary" name ="action" value="delete" formaction="DeleteItemServlet">Delete Item</button>
			<button type="submit" class="btn btn-primary" name="action" value="back" formaction="index.html">Back</button>
			
        </form>
    </div>
</body>

</html>