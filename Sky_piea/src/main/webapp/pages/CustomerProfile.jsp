<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%
    // Check if admin is logged in
    Integer customerId = (Integer) session.getAttribute("customer_id");

	if (customerId == null) {
    response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
	}

%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Profile</title>
    <link rel="stylesheet" href="../style/AdminProfile.css">
</head>
<body>
    <%@ include file="CustomerHeader.jsp" %>

    <p>User ID: <%= customerId %></p>

    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/sky_piea" user="root" password=""/>
    
    <sql:query dataSource="${dataSource}" var="userProfile">
        SELECT * FROM customer WHERE customerID = <%= customerId %>;
    </sql:query>
    
    <c:forEach var="row" items="${userProfile.rows}">
        <c:set var="userName" value="${row.customername}" />
        <c:set var="userEmail" value="${row.email}" />
        <c:set var="userNumber" value="${row.number}" />
    </c:forEach>
    
    <c:if test="${empty userProfile.rows}">
        <p>No user profile found for userID ${customerId}</p>
    </c:if>
    
    <div class="profile-container">
        <form action="../UpdateCustomerProfileServlet" method="POST">
            <div class="form-group">
                <input type="hidden" name="userId" value="<%= customerId %>">
                <label for="userName">Name:</label>
                <input type="text" id="userName" name="userName" value="<c:out value='${userName}' />" required>
            </div>
            <div class="form-group">
                <label for="userEmail">Email:</label>
                <input type="email" id="userEmail" name="userEmail" value="<c:out value='${userEmail}' />" required>
            </div>
            <div class="form-group">
                <label for="number">Number:</label>
                <input type="number" id="number" name="number" value="<c:out value='${userNumber}' />" required>
            </div>
            <div class="form-group">
                <label for="password">Change Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter new password">
            </div>
            <div class="form-group">
                <button type="submit" class="btn-save">Save Changes</button>
            </div>
        </form>
    </div>
</body>
</html>
