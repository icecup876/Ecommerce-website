<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
    <title>Your Cart</title>
    <link rel="stylesheet" href="../style/Cart.css">
</head>
<body>
    <%@ include file="CustomerHeader.jsp" %>
    <h1>Your Cart</h1>
    <div class="cart-container">
        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                                   url="jdbc:mysql://localhost:3306/sky_piea" user="root" password=""/>
                <sql:query dataSource="${dataSource}" var="cartItems">
                    SELECT p.productID, p.productName, c.quantity, p.price, (c.quantity * p.price) AS total
                    FROM cart c
                    INNER JOIN product p ON c.productID = p.productID
                    WHERE c.customerID = <%=customerId %>;
                </sql:query>
                <c:set var="grandTotal" value="0" />
                <c:forEach var="item" items="${cartItems.rows}">
                    <tr>
                        <td><c:out value="${item.productName}" /></td>
                        <td>
                            <form action="<c:url value='/UpdateCartServlet'/>" method="post">
                                <input type="hidden" name="productId" value="${item.productID}" />
                                <input type="hidden" name="customerId" value=<%=customerId %> />
                                <input type="number" name="quantity" value="${item.quantity}" min="1" max="99" />
                                <button type="submit">Update</button>
                            </form>
                        </td>
                        <td>$<c:out value="${item.price}" /></td>
                        <td>$<c:out value="${item.total}" /></td>
                        <td>
                            <form action="<c:url value='/RemoveCartItemsServlet'/>" method="post">
                                <input type="hidden" name="productId" value="${item.productID}" />
                                <input type="hidden" name="customerId" value=<%=customerId %> />
                                <button type="submit">Remove</button>
                            </form>
                        </td>
                    </tr>
                    <c:set var="grandTotal" value="${grandTotal + item.total}" />
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" class="text-right">Grand Total:</td>
                    <td colspan="2">$<c:out value="${grandTotal}" /></td>
                </tr>
                <tr>
                    <td colspan="5" class="text-center">
                        <form action="<c:url value='/CheckOutServlet'/>" method="post">
                            <button type="submit">Check Out</button>
                        </form>
                    </td>
                </tr>
            </tfoot>
        </table>
    </div>
</body>
</html>
