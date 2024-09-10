package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Database.DataBaseController;

/**
 * Servlet implementation class LoginCustomerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginCustomerServlet" })
public class LoginCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DataBaseController dbController = new DataBaseController();
   
    public LoginCustomerServlet() {
        super();
       
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Entered the login Customer Servlet");
		
		String customername = request.getParameter("customername");
		String password = request.getParameter("password");
		
		System.out.println(customername);
		System.out.println(password);
		
		int customerId = dbController.LoginCustomer(customername, password);
		
		if (customerId > 0) {
			// Sucessful login 
			
			HttpSession userSession = request.getSession();
			userSession.setAttribute("customer_id", customerId);
			userSession.setAttribute("customername", customername);
			userSession.setMaxInactiveInterval(30*30);
			
			System.out.println("CustomerID is " + customerId);
			
			Cookie userCookie = new Cookie("customer", customername);
			userCookie.setMaxAge(30*30);
			response.addCookie(userCookie);
			
			
			response.sendRedirect(request.getContextPath() + "/pages/Home.jsp");
			
		}else if (customerId == 0) {
			// Customer name password doesn't match
		    request.setAttribute("errorMessage", "Customer credentials incorrect. Please try again.");
		    request.getRequestDispatcher("/pages/LoginCustomer.jsp").forward(request, response);
		}else {
			// Server error or some other error
		    request.setAttribute("errorMessage", "An error occurred. Please try again later.");
		    request.getRequestDispatcher("/pages/LoginCustomer.jsp").forward(request, response);		}
	}

}
