package controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateCartServlet" })
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateCartServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve parameters from the form
		System.out.print("Servlet Entered");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/sky_piea";
        String username = "root";
        String password = ""; // Replace with your database password

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Prepare the SQL statement to update the quantity of the product in the cart
            String sql = "UPDATE cart SET quantity = ? WHERE productID = ? AND customerID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, quantity);
                statement.setInt(2, productId);
                statement.setInt(3, customerId);

                // Execute the update statement
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    // Redirect the user back to the cart page after successful update
                    response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp");
                } else {
                    // Handle the case where the item was not found in the cart
                    response.getWriter().println("Item not found in the cart.");
                }
            }
        } catch (SQLException e) {
            // Handle any database errors
            throw new ServletException("Error accessing database.", e);
        }
	}

}
