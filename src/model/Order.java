package model;

public class Order {

	int id;
	private String status;
	private int customer;
	private String date;
	
	Order(int id, String status, int customer, String date) {
		this.id = id;
		this.status = status;
		this.customer = customer;
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "Order #"+id;
	}
	
}
