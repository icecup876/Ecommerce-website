package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Util.StringUtilsSP;
import controller.Database.DataBaseController;
import Model.AddProductModel;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DataBaseController dbController = new DataBaseController();
	
    public AddProductServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String product_name = request.getParameter("productName");
		String priceString = request.getParameter("price");
		String description = request.getParameter("description");
		String stockString = request.getParameter("stock");
		Part imagePart = request.getPart("image");
		
		int price = 0;
		int stock = 0;


		try {
			if (priceString != null && !priceString.isEmpty()) {
				price = Integer.parseInt(priceString);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}

		try {
			if (stockString != null && !stockString.isEmpty()) {
		        stock = Integer.parseInt(stockString);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		// Create a StudentModel object to hold student information
				AddProductModel product = new AddProductModel(product_name, description, price, stock, imagePart);
				
				// Call DBController to register the student
				int result = dbController.addProduct(product);
				
				if (result == 1) {
					
					// Get the image file name from the student object (assuming it was extracted earlier)
					String fileName = product.getImageUrlFromPart();

					// Check if a filename exists (not empty or null)
					if (!fileName.isEmpty() && fileName != null) {
					  // Construct the full image save path by combining the directory path and filename
					  String savePath = StringUtilsSP.IMAGE_DIR_PRODUCT;
					  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
					}
					System.out.println("1");

					request.setAttribute(StringUtilsSP.MESSAGE_SUCCESS, StringUtilsSP.MESSAGE_SUCCESS_ADD);
					response.sendRedirect(request.getContextPath() + StringUtilsSP.ADMIN_PRODUCT_PAGE+ "?success=true");
				} else if (result == 0) {
					//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
					//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
					System.out.println("2");
				} else {
					//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
					//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
					System.out.println("3");
				}
				
			}

}
