package Model;

public class RegisterCustomerModel {
	
	private String customername;
	private String email;
	private String number;
	private String password;
	
	public RegisterCustomerModel(String customername, String email,String number, String password) {
		super();
		this.customername = customername;
		this.email = email;
		this.number = number;
		this.password = password;
		
		}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
