<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Login Page</title>
    <link rel="stylesheet" href="<%=contextPath%>/style/Login.css">
</head>
<body>
    <div class="login-container">
        <h2>Login as Customer</h2>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
        <form action="../LoginCustomerServlet" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="customername" name="customername" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <a href="<%=contextPath%>/pages/RegisterCustomer.jsp">Register now</a></p>
    </div>
</body>
</html>
