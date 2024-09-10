<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<%
    // Check if admin is logged in
    Integer userId = (Integer) session.getAttribute("user_id");

	if (userId == null) {
    response.sendRedirect(request.getContextPath() + "/pages/LoginAdmin.jsp");
	}

%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Profile</title>
    <link rel="stylesheet" href="../style/CustomerProfile.css">
</head>
<body>
    <%@ include file="AdminHeader.jsp" %>
    
    <p>User ID: <%= userId %></p>
    

    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/sky_piea" user="root" password=""/>
    
    <sql:query dataSource="${dataSource}" var="userProfile">
        SELECT * FROM users WHERE userID = <%=userId %>;
    </sql:query>
    
    <c:forEach var="row" items="${userProfile.rows}">
        <c:set var="userName" value="${row.username}" />
    	<c:set var="userEmail" value="${row.email}" />
    </c:forEach>
    
    <c:if test="${empty userProfile.rows}">
    	<p>No user profile found for userID ${userId}</p>
	</c:if>
    
    <div class="profile-container">
        <form action="../UpdateAdminProfile" method="POST">
            <div class="form-group">
            	<input type="hidden" name="userId" value="<%=userId%>">
                <label for="userName">Name:</label>
                <input type="text" id="userName" name="userName" value="<c:out value='${userName}' />" required>
            </div>
            <div class="form-group">
                <label for="userEmail">Email:</label>
                <input type="email" id="userEmail" name="userEmail" value="<c:out value='${userEmail}' />" required>
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
