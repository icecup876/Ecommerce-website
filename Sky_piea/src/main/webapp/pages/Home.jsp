<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Util.StringUtilsSP"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="CustomerHeader.jsp" %>

<%
Integer customerId = (Integer) session.getAttribute("customer_id");
String searchQuery = request.getParameter("search");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sky Piea Home</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/Home.css">
</head>
<body>
    <main>
        <section id="products">
            <h2>Available Products</h2>
            
            <!-- Search Form -->
            <form method="GET" action="<%=request.getContextPath()%>/HomeServlet">
                <input type="text" name="search" placeholder="Search products..." value="${empty searchQuery ? '' : searchQuery}">
                <button type="submit">Search</button>
            </form>
            
            <!-- Success Message -->
            <c:if test="${not empty requestScope.successMessage}">
                <div>${requestScope.successMessage}</div>
            </c:if>
    
            <!-- Error Message -->
            <c:if test="${not empty requestScope.errorMessage}">
                <div>${requestScope.errorMessage}</div>
            </c:if>
    
            <!-- Redirect Message -->
            <c:if test="${not empty requestScope.redirectMessage}">
                <div>${requestScope.redirectMessage}</div>
            </c:if>
    
            <div class="products-container">
                <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                            url="jdbc:mysql://localhost:3306/sky_piea" user="root" password=""/>
                <sql:query dataSource="${dataSource}" var="product">
                    <c:choose>
                        <c:when test="${empty searchQuery}">
                            SELECT * FROM product;
                        </c:when>
                        <c:otherwise>
                            SELECT * FROM product WHERE productName LIKE '%${searchQuery}%' OR price LIKE '%${searchQuery}%';
                        </c:otherwise>
                    </c:choose>
                </sql:query>
                <c:forEach items="${product.rows}" var="product">
                    <div class="product">
                        <img src="${pageContext.request.contextPath}/resources/images/product/${product.imageUrlFromPart}" alt="${product.productName}" style="width:200px; height:150px;">
                        <h3>${product.productName}</h3>
                        <p>${product.description}</p>
                        <p>Price: ${product.price}</p>
                        <form method="POST" action="<%=request.getContextPath()%>/CartServlet" onsubmit="return confirm('Are you sure you want to add this product to your cart?');">
                            <input type="hidden" name="customer_id" value=<%=customerId%>>
                            <input type="hidden" name="product_id" value="${product.productId}">
                            <input type="hidden" name="user_id" value=<%=customerId%>>
                            <button type="submit">Add to Cart</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>
</body>
</html>
