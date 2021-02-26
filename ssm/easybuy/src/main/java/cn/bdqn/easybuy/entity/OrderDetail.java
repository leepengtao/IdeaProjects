package cn.bdqn.easybuy.entity;

public class OrderDetail {

	private int id; 
	private String serialNumber;
	private int productId;
	private int quantity;
	private double cost;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public OrderDetail(int id, String serialNumber, int productId, int quantity, double cost) {
		this.id = id;
		this.serialNumber = serialNumber;
		this.productId = productId;
		this.quantity = quantity;
		this.cost = cost;
	}
}
