package Model;

public class UpdateAdminModel {
	
	private int userID;
	private String username;
	private String email;
	private String password;
	
	public UpdateAdminModel(int userID, String username,String email, String password) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;

		}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
