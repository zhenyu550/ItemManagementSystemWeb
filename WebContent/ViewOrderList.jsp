<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "order.dad.com.Order" %>
<%@ page import = "order.dad.com.OrderService" %>
<%@ page import = "java.time.LocalDateTime" %>
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Order List</title>
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
    <h1>Order List</h1>
    <div class="container">
        <form method="post" action="LoadOrderDataServlet">
            <table class="table">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Code</th>
                        <th>Order Date</th>
                        <th>Order Time</th>
                        <th>Select</th>
                    </tr>
                </thead>
                <tbody>
                    <%
						// Get the item list from database using Servlet
						List<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
						for(Order order: orders)
						{
							// Convert the date and time of the order to desired string format
							LocalDateTime orderDateTime = order.getDateTime();
							DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
							DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					%>
                    <tr>
                        <td><%=order.getId() %></td>
                        <td><%=order.getCode() %></td>
                        <td><%=orderDateTime.format(dateFormat) %></td>
                        <td><%=orderDateTime.format(timeFormat) %></td>
                        <%--Order Id is assigned as radio button value for data retrieval purposes--%>
                        <td><input type="radio" name="radioButton" value="<%=order.getId()%>"></td>
                    </tr>
                    <%
						}								
					%>
                </tbody>
            </table>

            <%--Buttons Section--%>
            <button type="submit" class="btn btn-primary" name="action" value="process_order">Process Order</button>
            <button type="submit" class="btn btn-primary" name="action" value="back"
                formaction="index.html">Back</button>
        </form>
    </div>
</body>

</html>