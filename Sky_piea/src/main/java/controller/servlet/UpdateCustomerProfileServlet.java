package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.UpdateCustomerModel;
import controller.Database.DataBaseController;

/**
 * Servlet implementation class UpdateCustomerProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateCustomerProfileServlet" })
public class UpdateCustomerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public UpdateCustomerProfileServlet() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String customer_id = request.getParameter("userId");
		String Customername = request.getParameter("userName");
		String Email = request.getParameter("userEmail");
		String Number = request.getParameter("number");
		String Password = request.getParameter("password");
		
		System.out.println(customer_id);
		System.out.println(Customername);
		System.out.println(Email);
		System.out.println(Number);
		System.out.println(Password);
		
		
		int customer_Id = 0;
		try {
			if (customer_id != null && !customer_id.isEmpty()) {
		        customer_Id = Integer.parseInt(customer_id);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		System.out.println(customer_Id);
		
		UpdateCustomerModel updateCustomerModel = new UpdateCustomerModel (customer_Id, Customername, Email, Number, Password);
		
		
		int result = dbController.UpdateCustomer(updateCustomerModel);
		
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
		} else {
			
		}
	}

}
