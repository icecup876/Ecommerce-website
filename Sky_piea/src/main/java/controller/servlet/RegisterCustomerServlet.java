package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.RegisterCustomerModel;
import controller.Database.DataBaseController;

/**
 * Servlet implementation class RegisterCustomerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterCustomerServlet" })
public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public RegisterCustomerServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String Customername = request.getParameter("username");
		String Email = request.getParameter("email");
		String Number = request.getParameter("number");
		String Password = request.getParameter("password");
		
		RegisterCustomerModel registerCustomerModel = new RegisterCustomerModel (Customername, Email,Number, Password);
		System.out.println("Here");
		int result = dbController.RegisterCustomer(registerCustomerModel);
		
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/LoginCustomer.jsp");
		} else {
			// 
		    request.setAttribute("errorMessage", "Please Enter unique username and phonenumber");
		    request.getRequestDispatcher("/pages/RegisterCustomer.jsp").forward(request, response);
		}
	}

}
