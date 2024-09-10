package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.RegisterModel;
import controller.Database.DataBaseController;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DataBaseController dbController = new DataBaseController();
    
    public RegisterServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String Username = request.getParameter("username");
		String Email = request.getParameter("email");
		String Password = request.getParameter("password");
		
		RegisterModel registerModel = new RegisterModel (Username, Email, Password);
		System.out.println("Here");
		int result = dbController.RegisterUser(registerModel);
		
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/Login.html");
		} else {
			// 
		    request.setAttribute("errorMessage", "Please Enter unique username and phonenumber");
		    request.getRequestDispatcher("/pages/Register.jsp").forward(request, response);
		}
		
	}

}
