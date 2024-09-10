<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	HttpSession userSession = request.getSession();
	String username = (String) userSession.getAttribute("username");
	String contextPath = request.getContextPath();
	%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sky Piea Admin Panel</title>
    <link rel="stylesheet" href="../style/AdminHeader.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">Sky Piea Admin</div>
        <ul class="navbar-nav">
            <li><a href="../pages/Product.jsp">Manage Products</a></li>
            <li><a href="../pages/Order.jsp">Manage Orders</a></li>
            <li><a href="../pages/AdminProfile.jsp">Profile</a></li>
            <li>
            	<form action="<%
            		
            		if (username != null) {
            			out.print(contextPath + "/LogoutServlet");
            		}else{
            			out.print(contextPath + "/pages/LoginAdmin.jsp");
            		}
            	
            	%>" method="post">
            		<input type="submit" value="<%
            		if (username != null) {
            			out.print("Logout");
            		}else {
            			out.print("Login");
            		}
            		
            		%>"/>
            	</form>

            </li>
        </ul>
    </nav>
