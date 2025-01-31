package Util;

public class StringUtilsSP {

	// Start: DB Connection
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL = "jdbc:mysql://localhost:3306/sky_piea";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";

	public static final String IMAGE_ROOT_PATH = "Users\\Subham\\eclipse-workspace\\Sky_piea\\src\\main\\webapp\\resources\\images\\";
	public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "product\\";
	// End: DB Connection

	// Start: Queries
	public static final String QUERY_ADD_PRODUCT = "INSERT INTO product (productName, description, price, stock, imageUrlFromPart) VALUES (?, ?, ?, ?, ?)";
	public static final String REGISTER = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
	public static final String REGISTER_CUSTOMER = "INSERT INTO customer (customername, email, number, password) VALUES (?, ?, ?, ?)";

	public static final String QUERY_LOGIN_USER_CHECK = "SELECT * FROM users WHERE username = ? AND password = ? ";
	public static final String QUERY_LOGIN_CUSTOMER_CHECK = "SELECT * FROM customer WHERE customername = ? AND password = ? ";
	//public static final String QUERY_GET_ALL_STUDENTS = "SELECT * FROM student_info";
	public static final String QUERY_GET_USER_ID = "SELECT id FROM student_info WHERE user_name = ?";
	public static final String ADD_PRODUCT_TO_CART = "INSERT INTO cart (customerID, productID, quantity) VALUES (?, ?, ?)";
	
	
	//public static final String ADD_PRODUCT_TO_CART = 
	
	
	///-------------------------------UPDATE QUERIES ------------------------------------///
	public static final String QUERY_DELETE_PRODUCT = "DELETE FROM product WHERE productId = ?";
	public static final String QUERY_UPDATE_PRODUCT = "UPDATE product SET productName = ?, description = ?, price = ?, stock = ?, imageUrlFromPart = ? WHERE productId = ?";
	public static final String QUERY_UPDATE_ADMIN = "UPDATE users SET username = ?, email = ?, password = ? WHERE userID = ?";
	public static final String QUERY_UPDATE_CUSTOMER = "UPDATE customer SET customername = ?, email = ?, number = ?, password = ? WHERE customerID = ?";
// End: Queries

	// Start: Parameter names
	public static final String USERNAME = "username";
	public static final String USER_NAME = "user_name";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String BIRTHDAY = "birthday";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SUBJECT = "subject";
	public static final String PASSWORD = "password";
	public static final String RETYPE_PASSWORD = "retypePassword";
	public static final String IMAGE = "image";
	// End: Parameter names

	// Start: Validation Messages
	// Register Page Messages
	public static final String MESSAGE_SUCCESS_ADD = "Successfully Added!";
	public static final String MESSAGE_SUCCESS_UPDATE = "Successfully Updated!";
	public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
	public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
	public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
	public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
	public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
	public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";

	// Login Page Messages
	public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
	public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
	public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

	// Other Messages
	public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
	public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
	public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";
	
	public static final String MESSAGE_SUCCESS = "successMessage";
	public static final String MESSAGE_ERROR = "errorMessage";
	public static final String MESSAGE_REDIRECT = "redirectMessage";
	// End: Validation Messages

	// Start: JSP Route
	public static final String ADMIN_PRODUCT_PAGE = "/pages/Product.jsp";
	public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
	public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
	public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
	public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
	public static final String PAGE_URL_HEADER = "pages/header.jsp";
	public static final String URL_LOGIN = "/login.jsp";
	public static final String URL_INDEX = "/index.jsp";
	public static final String URL_HOME = "/pages/Home.jsp";
	// End: JSP Route

	// Start: Servlet Route
	public static final String SERVLET_URL_LOGIN = "/login";
	public static final String SERVLET_URL_REGISTER = "/registerstudent";
	public static final String SERVLET_URL_LOGOUT = "/logout";
	public static final String SERVLET_URL_HOME = "/home";
	public static final String SERVLET_URL_MODIFY_USER = "/modifyUser";
	// End: Servlet Route

	// Start: Normal Text
	public static final String USER = "user";
	public static final String SUCCESS = "success";
	public static final String TRUE = "true";
	public static final String JSESSIONID = "JSESSIONID";
	public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String STUDENT_MODEL = "studentModel";
	public static final String STUDENT_LISTS = "studentLists";
	public static final String SLASH= "/";
	public static final String DELETE_ID= "deleteId";
	public static final String UPDATE_ID= "updateId";
	
	
	// End: Normal Text
	
	
	//// Custome URLS
	
	
	
}