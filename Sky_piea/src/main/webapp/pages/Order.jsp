<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="AdminHeader.jsp" %>
<%
    // Check if admin is logged in
    Integer userId = (Integer) session.getAttribute("user_id");

	if (userId == null) {
    response.sendRedirect(request.getContextPath() + "/pages/LoginAdmin.jsp");
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Orders</title>
<link rel="stylesheet" href="<%=contextPath%>/style/Order.css">
</head>
<body>

<h2>Admin Orders</h2>

<table border="1">
    <tr>
        <th>Order ID</th>
        <th>Customer ID</th>
        <th>Product ID</th>
        <th>Quantity</th>
        <th>Total Amount</th>
        <th>Order Date</th>
        <th>Status</th>
    </tr>
    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/sky_piea" user="root"
        password="" />
    <sql:query dataSource="${dataSource}" var="orders">
        SELECT * FROM orders;
    </sql:query>
    <c:forEach var="order" items="${orders.rows}">
        <tr>
            <td>${order.orderID}</td>
            <td>${order.customerID}</td>
            <td>${order.productID}</td>
            <td>${order.quantity}</td>
            <td>${order.totalAmount}</td>
            <td>${order.orderDate}</td>
            <td>${order.status}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
