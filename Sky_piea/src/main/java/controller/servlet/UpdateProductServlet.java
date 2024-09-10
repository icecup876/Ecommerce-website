package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.UpdateProductModel;
import Util.StringUtilsSP;
import controller.Database.DataBaseController;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProductServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
      DataBaseController dbController = new DataBaseController();
    
    public UpdateProductServlet() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// Getting parameter data 
		String productIdString = request.getParameter("productId");
		String product_name = request.getParameter("productName");
		String priceString = request.getParameter("price");
		String description = request.getParameter("description");
		String stockString = request.getParameter("stock");
		Part imagePart = request.getPart("imageUrl");
		
		// Creating variables 
		int productId = 0;
		int price = 0;
		int stock = 0;
		
		// Converting the string data into number 
		try {
			if (productIdString != null && !productIdString.isEmpty()) {
				productId = Integer.parseInt(productIdString);}
		} catch (NumberFormatException e) {
		    // Handle parsing error for stock
		}
		
		
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
		
		System.out.println(productId);
		System.out.println(product_name);
		System.out.println(description);
		System.out.println(price);
		System.out.println(imagePart);
		System.out.println(imagePart.getSubmittedFileName());
		
		UpdateProductModel product = new UpdateProductModel(productId, product_name, description, price, stock, imagePart.getSubmittedFileName());
		
		int result = dbController.UpdateProduct(product);
		
		if (result == 1) {
			
			// Get the image file name from the student object (assuming it was extracted earlier)
			String fileName = product.getImageUrlFromPart();

			// Check if a filename exists (not empty or null)
			if (!fileName.isEmpty() && fileName != null) {
			  // Construct the full image save path by combining the directory path and filename
			  String savePath = StringUtilsSP.IMAGE_DIR_PRODUCT;
			  imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
			}

			request.setAttribute(StringUtilsSP.MESSAGE_SUCCESS, StringUtilsSP.MESSAGE_SUCCESS_UPDATE);
			response.sendRedirect(request.getContextPath() + StringUtilsSP.ADMIN_PRODUCT_PAGE+ "?success=true");
		} else if (result == 0) {
			request.setAttribute(StringUtilsSP.MESSAGE_ERROR, StringUtilsSP.MESSAGE_ERROR_REGISTER);
			request.getRequestDispatcher(StringUtilsSP.ADMIN_PRODUCT_PAGE).forward(request, response);
		} else {
			//request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
			//request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
		}
		
	}

}
