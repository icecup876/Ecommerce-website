package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.UpdateAdminModel;
import controller.Database.DataBaseController;

/**
 * Servlet implementation class UpdateAdminProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAdminProfile" })
public class UpdateAdminProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DataBaseController dbController = new DataBaseController();
    
    public UpdateAdminProfile() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String admin_id = request.getParameter("userId");
		String Username = request.getParameter("userName");
		String Email = request.getParameter("userEmail");
		String Password = request.getParameter("password");
		
		System.out.println(admin_id);
		System.out.println(Username);
		System.out.println(Email);
		System.out.println(Password);
		
		
		int admin_Id = 0;
		try {
			if (admin_id != null && !admin_id.isEmpty()) {
		        admin_Id = Integer.parseInt(admin_id);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		System.out.println(admin_Id);
		
		UpdateAdminModel updateAdminModel = new UpdateAdminModel (admin_Id, Username, Email, Password);
		
		
		int result = dbController.UpdateUser(updateAdminModel);
		
		
		if (result > 0) {
			response.sendRedirect(request.getContextPath() + "/pages/LoginAdmin.jsp");
		} else {
			
		}
	}

}
