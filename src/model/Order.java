package model;

public class Order {

	private int id;
	
	Order(int id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "Order #"+id;
	}
	
}
