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
 * Servlet implementation class Login
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DataBaseController dbController = new DataBaseController();
  
    public LoginServlet() {
        super();
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		int userId = dbController.LoginUser(username, password);
		
		if (userId > 0) {
			// Sucessful login 
			
			HttpSession userSession = request.getSession();
			userSession.setAttribute("user_id", userId);
			userSession.setAttribute("username", username);
			userSession.setMaxInactiveInterval(30*30);
			
			System.out.println("UserID is " + userId);
			
			Cookie userCookie = new Cookie("user", username);
			userCookie.setMaxAge(30*30);
			response.addCookie(userCookie);
			
			
			response.sendRedirect(request.getContextPath() + "/pages/Product.jsp");
			
		}else if (userId == 0) {
			// Admin name password doesn't match
		    request.setAttribute("errorMessage", "Admin credentials incorrect. Please try again.");
		    request.getRequestDispatcher("/pages/LoginAdmin.jsp").forward(request, response);
		}else {
			// Server error or some other error
		    request.setAttribute("errorMessage", "An error occurred. Please try again later.");
		    request.getRequestDispatcher("/pages/LoginAdmin.jsp").forward(request, response);
		}
	}

}
