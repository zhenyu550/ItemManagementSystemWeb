<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Add Item Page</title>
</head>
<style>
</style>

<body>

    <h1>Add New Item</h1>
    <div class="container">
        <form method="post" action="AddItemServlet">
            <div class="form-group">
                <label for="item_name">Item Name:</label>
                <input type="text" class="form-control" placeholder="Enter Item Name" name="item_name">
            </div>
            <div class="form-group">
                <label for="item_code">Item Code:</label>
                <input type="text" class="form-control" placeholder="Enter Item Code" name="item_code">
            </div>
            <div class="form-group">
                <label for="item_price">Item Price:</label>
                <input type="number" class="form-control" placeholder="Enter Item Price" name="item_price" min=0 step=0.01>
            </div>
            <div class="form-group">
                <label for="item_stock">Item Stock:</label>
                <input type="number" class="form-control" placeholder="Enter Item Stock" name="item_stock" min=0>
            </div>
            <div class="form-group">
                <label for="item_desc">Item Description:</label>
                <textarea class="form-control" rows="5" placeholder="Enter Item Description"
                    name="item_desc"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <button type="reset" class="btn btn-primary">Reset</button>
            <button type="submit" class="btn btn-primary" formaction="ViewItemListServlet">Back</button>
        </form>
    </div>

</body>

</html>