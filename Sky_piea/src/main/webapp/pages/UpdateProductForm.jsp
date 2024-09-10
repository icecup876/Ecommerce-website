<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Product - Sky Piea Admin Panel</title>
    <link rel="stylesheet" href="../style/UpdateProduct.css">
</head>
<body>
    <header class="main-header">
        <h1>Update Product</h1>
    </header>
     <% String productIdStr = request.getParameter("productId");
        	Integer productId = Integer.parseInt(productIdStr);
        	%>
    <div class="form-container">
        <form action="../UpdateProductServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="<%=productId%>">
            <div class="form-group">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price">
            </div>
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="number" id="stock" name="stock">
            </div>
            <div class="form-group">
                <label for="imageFile">Update Image:</label>
                <input type="file" id="imageFile" name="imageUrl" accept="image/*">
            </div>
            <button type="submit">Update Product</button>
        </form>
    </div>
</body>
</html>
