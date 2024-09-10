<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	HttpSession userSession = request.getSession();
	String customername = (String) userSession.getAttribute("customername");
	String contextPath = request.getContextPath();
	%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sky Piea</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/CustomerHeader.css">
</head>
<body>

<header>
    <div class="header-content">
        <img src="${pageContext.request.contextPath}/resources/images/product/Logo.jpg" alt="Sky Piea Logo" class="logo">
        <h1>Sky Piea</h1>
    </div>
    <nav>
        <ul>
        	<li><a href="<%=contextPath%>/pages/Home.jsp">Home</a></li>
            <li><a href="<%=contextPath%>/pages/CustomerOrders.jsp">Orders</a></li>
            <li><a href="<%=contextPath%>/pages/AboutUs.jsp">About Us</a></li>
            <li><a href="<%=contextPath%>/pages/Cart.jsp">Cart</a></li>
            <li><a href="<%=contextPath%>/pages/CustomerProfile.jsp">Profile</a></li>
            <li>
            	<form action="<%
            		
            		if (customername != null) {
            			out.print(contextPath + "/LogoutServlet");
            		}else{
            			out.print(contextPath + "/pages/LoginOptions.jsp");
            		}
            	
            		%>" method="post">
            		<input type="submit" value="<%
            		if (customername != null) {
            			out.print("Logout");
            		}else {
            			out.print("Login");
            		}
            		
            		%>"/>
            		</form>
             </li>
        </ul>
    </nav>
</header>

</body>
</html>
