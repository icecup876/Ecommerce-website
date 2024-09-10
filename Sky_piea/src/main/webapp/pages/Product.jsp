<%@page import="Util.StringUtilsSP"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ include file="AdminHeader.jsp" %>
<% String currentContextPath = request.getContextPath(); 
//Check if admin is logged in
Integer userId = (Integer) session.getAttribute("user_id");

if (userId == null) {
response.sendRedirect(request.getContextPath() + "/pages/LoginAdmin.jsp");
}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Products - Sky Piea Admin Panel</title>
<link rel="stylesheet" href="<%=contextPath%>/style/Product.css">
</head>
<body>
    <header class="main-header">
    	
        <h1>Manage Products</h1>
    </header>
    
    <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                        url="jdbc:mysql://localhost:3306/sky_piea" user="root" password=""/>
    <sql:query dataSource="${dataSource}" var="productList">
        SELECT * FROM product;
    </sql:query>
    
    <div class="admin-panel">
        <button class="btn-add" onclick="toggleAddProductForm()">Add Product</button>
        <div id="addProductForm" style="display: none; margin-top: 20px;">
            <form action="<%=contextPath%>/AddProductServlet" method="post" enctype="multipart/form-data">
                <label for="productName">Name:</label>
                <input type="text" id="productName" name="productName"><br>
                <label for="description">Description:</label>
                <textarea id="description" name="description"></textarea><br>
                <label for="price">Price:</label>
                <input type="number" id="price" name="price"><br>
                <label for="stock">Stock:</label>
                <input type="number" id="stock" name="stock"><br>
                <label for="imageFile">Image File:</label>
                <input type="file" id="image" name="image" accept="image/*"><br>
                <button type="submit">Submit</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${productList.rows}">
                    <tr>
                        <td>${product.productID}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        <td>${product.price}</td>
                        <td>
                        <form action="UpdateProductForm.jsp?productId=${product.productId}" method="post" onclick="return confirm('Are you sure you want to Update this product?');">
                   			<input type="hidden" name="productId" value="${product.productId}" />
                            	<button class="btn-update" >Update</button>
                        </form>
                        <form action="../DeleteProductServlet" method="post" onclick="return confirm('Are you sure you want to Delete this product?');">
                   			<input type="hidden" name="productId" value="${product.productId}" />
                            <button class="btn-delete">Delete</button>
                        </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        function toggleAddProductForm() {
            var form = document.getElementById('addProductForm');
            if (form.style.display === 'none' || form.style.display === '') {
                form.style.display = 'block';
            } else {
                form.style.display = 'none';
            }
        }
        
 
    </script>

    <%
        String errMsg = (String) request.getAttribute(StringUtilsSP.MESSAGE_ERROR);
        String successMsg = (String) request.getAttribute(StringUtilsSP.MESSAGE_SUCCESS);
        if (errMsg != null) {
    %>
    <h4 class="error-msg"><%= errMsg %></h4>
    <%
        }
        if (successMsg != null) {
    %>
    <h4 class="success-msg"><%= successMsg %></h4>
    <%
        }
    %>
</body>
</html>
