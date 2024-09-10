package controller.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CheckOutServlet" })
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckOutServlet() {
        super();
        
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("here");
		
		// Assuming you retrieve the user ID from the session or request parameter
		int userId = Integer.parseInt(request.getParameter("userId"));
		

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/sky_piea";
        String username = "root";
        String password = ""; // Update with your database password

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Get cart items for the user
            String getCartQuery = "SELECT c.productID, c.quantity, (c.quantity * p.price) AS totalAmount, p.price " +
                                  "FROM cart c " +
                                  "JOIN product p ON c.productID = p.productID " +
                                  "WHERE c.customerID = ?";
            try (PreparedStatement getCartStatement = conn.prepareStatement(getCartQuery)) {
                getCartStatement.setInt(1, userId);
                ResultSet cartItems = getCartStatement.executeQuery();

                // Insert each cart item into the orders table
                String insertOrderQuery = "INSERT INTO orders (customerID, productID, quantity, totalAmount, status) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement insertOrderStatement = conn.prepareStatement(insertOrderQuery)) {
                    while (cartItems.next()) {
                        int productId = cartItems.getInt("productID");
                        int quantity = cartItems.getInt("quantity");
                        BigDecimal totalAmount = cartItems.getBigDecimal("totalAmount");

                        // Set parameters for the insert statement
                        insertOrderStatement.setInt(1, userId);
                        insertOrderStatement.setInt(2, productId);
                        insertOrderStatement.setInt(3, quantity);
                        insertOrderStatement.setBigDecimal(4, totalAmount);
                        insertOrderStatement.setString(5, "Pending"); // Setting the status to pending 
                        insertOrderStatement.executeUpdate();
                    }
                }

                // Clear the user's cart after placing the order
                String clearCartQuery = "DELETE FROM cart WHERE customerID = ?";
                try (PreparedStatement clearCartStatement = conn.prepareStatement(clearCartQuery)) {
                    clearCartStatement.setInt(1, userId);
                    clearCartStatement.executeUpdate();
                }
            }

            // Redirect to a confirmation page
            response.sendRedirect(request.getContextPath() + "/Home.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error accessing database.", e);
        }

	}

}
