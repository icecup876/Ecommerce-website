package controller.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.RegisterModel;
import Model.UpdateAdminModel;
import Model.UpdateCustomerModel;
import Model.UpdateProductModel;
import Util.StringUtilsSP;

import Model.AddProductModel;
import Model.AdddToCartModel;
import Model.RegisterCustomerModel;

public class DataBaseController {
	/// Connecting to the database
	
		public Connection getConnection() throws SQLException, ClassNotFoundException {

			// Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
			Class.forName(StringUtilsSP.DRIVER_NAME);

			// Create a connection to the database using the provided credentials
			return DriverManager.getConnection(StringUtilsSP.LOCALHOST_URL, StringUtilsSP.LOCALHOST_USERNAME,
					StringUtilsSP.LOCALHOST_PASSWORD);
			}
		
		/// -------  REGISTER User/ Admin ------------------///
		public int RegisterUser( RegisterModel registerModel) {
			try (Connection con = getConnection()){
				PreparedStatement st = con.prepareStatement(StringUtilsSP.REGISTER);
				
				st.setString(1, registerModel.getUsername());
				st.setString(2, registerModel.getPassword());
				st.setString(3, registerModel.getEmail());
				
				int result = st.executeUpdate();
				return result > 0 ? 1 : 0;
				
			}catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
				return -1;
			}
		}
		
		
		/// ------- Update Admin Profile ------------------///
				public int UpdateUser( UpdateAdminModel updateAdminModel) {
					try (Connection con = getConnection()){
						PreparedStatement st = con.prepareStatement(StringUtilsSP.QUERY_UPDATE_ADMIN);
						
						st.setString(1, updateAdminModel.getUsername());
						st.setString(2, updateAdminModel.getEmail());
						st.setString(3, updateAdminModel.getPassword());
						st.setInt(4, updateAdminModel.getUserID());
						
						
						int result = st.executeUpdate();
						return result > 0 ? 1 : 0;
						
						
					}catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
						return -1;
					}
				}
		
		
		

		/// ------- Login Admin------------------///
		public int LoginUser( String username, String password) {
			try (Connection con = getConnection()){
				PreparedStatement st = con.prepareStatement(StringUtilsSP.QUERY_LOGIN_USER_CHECK);
				
				st.setString(1, username);
				st.setString(2, password);
				ResultSet result = st.executeQuery();
				if(result.next()) {
					/// IF the admin name and pass matches
					return result.getInt("userID");
				}else {
					return 0;
				}
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
		}
		
		
		/// -------  REGISTER Customer ------------------///
				public int RegisterCustomer( RegisterCustomerModel registerCustomerModel) {
					try (Connection con = getConnection()){
						PreparedStatement st = con.prepareStatement(StringUtilsSP.REGISTER_CUSTOMER);
						
						st.setString(1, registerCustomerModel.getCustomername());
						st.setString(2, registerCustomerModel.getEmail());
						st.setString(3, registerCustomerModel.getNumber());
						st.setString(4, registerCustomerModel.getPassword());
						
						int result = st.executeUpdate();
						return result > 0 ? 1 : 0;
						
					}catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
						return -1;
					}
				}
				
				
				
				/// ------- Update Customer Profile ------------------///
				public int UpdateCustomer( UpdateCustomerModel updateCustomerModel) {
					try (Connection con = getConnection()){
						PreparedStatement st = con.prepareStatement(StringUtilsSP.QUERY_UPDATE_CUSTOMER);
						
						st.setString(1, updateCustomerModel.getCustomername());
						st.setString(2, updateCustomerModel.getEmail());
						st.setString(3, updateCustomerModel.getNumber());
						st.setString(4, updateCustomerModel.getPassword());
						st.setInt(5, updateCustomerModel.getCustomerID());
						
						
						int result = st.executeUpdate();
						return result > 0 ? 1 : 0;
						
						
					}catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
						return -1;
					}
				}
				
				
				
				
				/// ------- Login Customer------------------///
				public int LoginCustomer( String customername, String password) {
					try (Connection con = getConnection()){
						PreparedStatement st = con.prepareStatement(StringUtilsSP.QUERY_LOGIN_CUSTOMER_CHECK);
						
						st.setString(1, customername);
						st.setString(2, password);
						ResultSet result = st.executeQuery();
						if(result.next()) {
							/// IF the customer name and pass matches
							return result.getInt("customerID");
						}else {
							return 0;
						}
				}catch (SQLException | ClassNotFoundException ex) {
					ex.printStackTrace();
					return -1;
				}
				}
		
		
		
		public int addProduct(AddProductModel product) {

			try {
				// Prepare a statement using the predefined query for student registration
				PreparedStatement stmt = getConnection().prepareStatement(StringUtilsSP.QUERY_ADD_PRODUCT);

				// Set the student information in the prepared statement
				stmt.setString(1, product.getProductName());
				stmt.setString(2, product.getDescription());
				stmt.setDouble(3, product.getPrice());
				stmt.setInt(4, product.getStock()); 
				stmt.setString(5, product.getImageUrlFromPart());
				
				System.out.println("Here");

				// Execute the update statement and store the number of affected rows
				int result = stmt.executeUpdate();
				System.out.println("Here2");

				// Check if the update was successful (i.e., at least one row affected)
				if (result > 0) {
					System.out.println("Here3");
					return 1; // Registration successful
				} else {
					System.out.println("Here4");
					return 0; // Registration failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
					// Print the stack trace for debugging purposes
					ex.printStackTrace();
					return -1; // Internal error
			}
		}
		
		
		public int UpdateProduct(UpdateProductModel product) {

			try {
				// Prepare a statement using the predefined query for student registration
				PreparedStatement stmt = getConnection().prepareStatement(StringUtilsSP.QUERY_UPDATE_PRODUCT);

				// Set the student information in the prepared statement
				
				stmt.setString(1, product.getProductName());
				stmt.setString(2, product.getDescription());
				stmt.setDouble(3, product.getPrice());
				stmt.setInt(4, product.getStock()); 
				stmt.setString(5, product.getImageUrlFromPart());
				stmt.setInt(6,product.getProductId());
				
				System.out.println("Here");

				// Execute the update statement and store the number of affected rows
				int result = stmt.executeUpdate();
				System.out.println("Here2");

				// Check if the update was successful (i.e., at least one row affected)
				if (result > 0) {
					System.out.println("Here3");
					return 1; // Registration successful
				} else {
					System.out.println("Here4");
					return 0; // Registration failed (no rows affected)
				}

			} catch (ClassNotFoundException | SQLException ex) {
					// Print the stack trace for debugging purposes
					ex.printStackTrace();
					return -1; // Internal error
			}
		}
		
		
			// ADD TO CART CODE
				public int addProductToCart(AdddToCartModel cart) {

					try {
						// Prepare a statement using the predefined query for student registration
						PreparedStatement stmt = getConnection().prepareStatement(StringUtilsSP.ADD_PRODUCT_TO_CART);
						
						stmt.setInt(1, cart.getCustomerID());
						stmt.setInt(2, cart.getProductID());
						stmt.setInt(3, cart.getQuantity());
						
						
						int result = stmt.executeUpdate();
						return result > 0 ? 1 : 0;
						
					}catch (SQLException | ClassNotFoundException ex) {
						ex.printStackTrace();
						return -1;
					}
						
				}

		
}