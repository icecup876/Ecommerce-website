<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="CustomerHeader.jsp" %>

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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style/AboutUs.css">
</head>
<body>
    <div class="container">
        <h1>About Us</h1>
        <p>Welcome to our website! We are dedicated to providing you with the best services and products in the industry.</p>
        <p>Our mission is to exceed your expectations and deliver exceptional value. With our team of experts, we strive to make your experience with us enjoyable and hassle-free.</p>
        <p>Feel free to contact us via phone or email, or use the form below to send us a message. We'd love to hear from you!</p>
        
        <div class="contact-info">
            <h2>Contact Information</h2>
            <p>Phone: 123-456-7890</p>
            <p>Email: info@example.com</p>
        </div>
        
        <div class="message-form">
            <h2>Send Us a Message</h2>
            <form action="#" method="post">
                <label for="name">Your Name:</label>
                <input type="text" id="name" name="name" required>
                
                <label for="email">Your Email:</label>
                <input type="email" id="email" name="email" required>
                
                <label for="message">Message:</label>
                <textarea id="message" name="message" required></textarea>
                
                <input type="submit" value="Send Message">
            </form>
        </div>
    </div>
</body>
</html>
