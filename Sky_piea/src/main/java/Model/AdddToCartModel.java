package Model;

public class AdddToCartModel {
	private int customerID;
	private int productID;
	private int quantity;
	
	public AdddToCartModel (int customerID, int productID, int quantity) {
		super();
		this.customerID = customerID;
		this.productID = productID;
		this.quantity = quantity;
		
		}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
