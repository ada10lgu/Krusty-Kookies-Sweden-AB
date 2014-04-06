package model;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderInfo {
	
	Order o;
	HashMap<Product,Integer> products;
	ArrayList<Pallet> pallets;
	
	public OrderInfo(Order o) {
		this.o = o;
		products = new HashMap<>();
		pallets = new ArrayList<>();
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
		
		sb.append("\nPallets:\n");
		for (Pallet p : pallets) {
			sb.append("#").append(p.id);
			sb.append("\t").append(p.p.getName());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
