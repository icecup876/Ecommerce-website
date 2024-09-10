<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="CustomerHeader.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%
    // Get customerID from session or request parameter
    Integer customerID = (Integer) session.getAttribute("customer_id");
    if (customerID == null) {
        // Redirect to login page if customerID is not set in session
        response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Orders</title>
    <link rel="stylesheet" href="../style/CustomerOrders.css">
</head>
<body>
    <h1>Your Orders</h1>
    <div class="order-container">
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Total Amount</th>
                    <th>Order Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <!-- Query orders for the current customer -->
                <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                                   url="jdbc:mysql://localhost:3306/sky_piea" user="root" password=""/>
                <sql:query dataSource="${dataSource}" var="orders">
                    SELECT o.orderID, p.productName, o.quantity, o.totalAmount, o.orderDate, o.status 
                    FROM orders o
                    INNER JOIN product p ON o.productID = p.productID
                    WHERE o.customerID = <%= customerID %> ORDER BY o.orderDate DESC;
                </sql:query>
                <c:forEach var="order" items="${orders.rows}">
                    <tr>
                        <td><c:out value="${order.orderID}" /></td>
                        <td><c:out value="${order.productName}" /></td>
                        <td><c:out value="${order.quantity}" /></td>
                        <td>$<c:out value="${order.totalAmount}" /></td> <!-- Display total amount -->
                        <td><c:out value="${order.orderDate}" /></td>
                        <td><c:out value="${order.status}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
