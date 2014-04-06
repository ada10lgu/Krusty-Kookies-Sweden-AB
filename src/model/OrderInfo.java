package model;

import java.util.HashMap;

public class OrderInfo {
	
	Order o;
	HashMap<Product,Integer> products;
	
	
	public OrderInfo(Order o) {
		this.o = o;
		products = new HashMap<>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(o).append("\n");
		sb.append("\nSummation of order:\n");
		for (Product p: products.keySet()) {
			sb.append("* ");
			sb.append(products.get(p)).append("\t");
			sb.append(p.getName()).append("\n");
		}
		
		return sb.toString();
	}
}
