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
    <title>Edit Item Page</title>
</head>

<body>

    <%						
	// Get the item data from Load Item Data Servlet
	Item item = (Item) request.getAttribute("item");
    %>

    <h1>Edit Item Details</h1>

    <div class="container">
        <form method="post" action="EditItemServlet">
            <div class="form-group">
                <label for="item_id">Item Id:</label>
                <input type="text" class="form-control" placeholder="Enter Item Id" name="item_id"
                    value="<%=item.getId()%>" readonly>
            </div>

            <div class="form-group">
                <label for="item_name">Item Name:</label>
                <input type="text" class="form-control" placeholder="Enter Item Name" name="item_name"
                    value="<%=item.getName()%>" readonly>
            </div>
            <div class="form-group">
                <label for="item_code">Item Code:</label>
                <input type="text" class="form-control" placeholder="Enter Item Code" name="item_code"
                    value="<%=item.getCode()%>" readonly>
            </div>
            <div class="form-group">
                <label for="item_price">Item Price:</label>
                <input type="number" class="form-control" placeholder="Enter Item Price" name="item_price" 
                    value="<%=item.getPrice()%>" min=0 step=0.01>
            </div>
            <div class="form-group">
                <label for="item_stock">Item Stock:</label>
                <input type="number" class="form-control" placeholder="Enter Item Stock" name="item_stock" 
                    value="<%=item.getStockAmount()%>" min=0>
            </div>
            <div class="form-group">
                <label for="item_desc">Item Description:</label>
                <textarea class="form-control" rows="5" placeholder="Enter Item Description" name="item_desc"><%=item.getDescription()%></textarea>
            </div>

            <button type="submit" class="btn btn-primary" name="action" value="save">Save</button>
            <button type="reset" class="btn btn-primary" name="action" value="reset">Reset</button>
            <button type="submit" class="btn btn-primary" formaction="ViewItemListServlet">Back</button>
        </form>
    </div>

</body>

</html>