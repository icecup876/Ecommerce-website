package Model;

public class UpdateCustomerModel {
	
	private int customerID;
	private String customername;
	private String email;
	private String number;
	private String password;
	
	public UpdateCustomerModel(int customerID, String customername,String email, String number, String password) {
		super();
		this.customerID = customerID;
		this.customername = customername;
		this.email = email;
		this.number = number;
		this.password = password;

		}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
