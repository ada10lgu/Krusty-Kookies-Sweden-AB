package model;

public class Order {

	int id;
	String status;
	Customer customer;
	String date;

	Order(int id, String status, Customer customer, String date) {
		this.id = id;
		this.status = status;
		this.customer = customer;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order #" + id;
	}

}
