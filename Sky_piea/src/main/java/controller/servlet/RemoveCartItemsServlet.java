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
 * Servlet implementation class RemoveCartItemsServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveCartItemsServlet" })
public class RemoveCartItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RemoveCartItemsServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve the product ID to be removed from the cart
        int productId = Integer.parseInt(request.getParameter("productId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/sky_piea";
        String username = "root";
        String password = ""; // Update with your database password

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Prepare the SQL statement to delete the cart item
            String sql = "DELETE FROM cart WHERE productID = ? AND customerID = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, productId);
                statement.setInt(2, customerId);

                // Execute the delete statement
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    // Redirect the user back to the cart page
                	response.sendRedirect(request.getContextPath() + "/pages/Cart.jsp");
                } else {
                    // Handle the case where the item was not found
                    response.getWriter().println("Item not found in the cart.");
                }
            }
        } catch (SQLException e) {
            // Handle any database errors
            throw new ServletException("Error accessing database.", e);
        }
		
	}

}
