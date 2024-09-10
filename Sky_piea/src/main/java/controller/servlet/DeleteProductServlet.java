package controller.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.StringUtilsSP;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/DeleteProductServlet" })
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteProductServlet() {
        super();
      
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String productIdString = request.getParameter("productId");
		Connection con = null;
        PreparedStatement st = null;
        
        int productId = 0;
        
        try {
			if (productIdString != null && !productIdString.isEmpty()) {
				productId = Integer.parseInt(productIdString);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}

        try {
            Class.forName(StringUtilsSP.DRIVER_NAME);
            con = DriverManager.getConnection(StringUtilsSP.LOCALHOST_URL,StringUtilsSP.LOCALHOST_USERNAME, StringUtilsSP.LOCALHOST_PASSWORD);

            String sql = (StringUtilsSP.QUERY_DELETE_PRODUCT); /// Can be used from String Utils
            
            st = con.prepareStatement(sql);
            
            st.setInt(1, productId);
            
            st.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace(); // Proper error handling should be implemented
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
		
	}
	
    }

}
