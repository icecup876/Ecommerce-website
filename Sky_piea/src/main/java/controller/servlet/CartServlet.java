package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.AdddToCartModel;
import Util.StringUtilsSP;
import controller.Database.DataBaseController;


/**
 * Servlet implementation class CartServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CartServlet" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public CartServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String customer_id = request.getParameter("customer_id");
		String product_id = request.getParameter("product_id");
	    
	    int quantity = 1 ;
	    
	    
	    int product_Id = 0;
	    int customer_Id = 0;

		
		
		try {
			if (customer_id != null && !customer_id.isEmpty()) {
				customer_Id = Integer.parseInt(customer_id);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		try {
			if (product_id != null && !product_id.isEmpty()) {
				product_Id = Integer.parseInt(product_id);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
	    
		System.out.println(customer_Id);
		System.out.println(product_Id);
		
	    AdddToCartModel addToCartModel = new AdddToCartModel(customer_Id, product_Id, quantity);
		
		// Call DBController to register the student
			int result = dbController.addProductToCart(addToCartModel);
			
			if (result > 0) {
				request.setAttribute(StringUtilsSP.MESSAGE_SUCCESS, "Added to the cart");
				request.getRequestDispatcher(StringUtilsSP.URL_HOME).forward(request, response);
				
			} else if (result == 0) {
				request.setAttribute("redirectMessage", "Your redirect message goes here");
				request.getRequestDispatcher(StringUtilsSP.URL_HOME).forward(request, response);
			} else {
				request.setAttribute(StringUtilsSP.MESSAGE_ERROR, "LOGIN FIRST");
				request.getRequestDispatcher(StringUtilsSP.URL_HOME).forward(request, response);
			}
	}

}
