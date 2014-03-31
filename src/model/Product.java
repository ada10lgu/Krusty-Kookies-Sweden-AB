package model;

import java.util.TreeMap;

public class Product {

	private TreeMap<Article, Double> ingridients;
	private String name;

	Product(String name, TreeMap<Article, Double> ingridients) {
		this.name = name;
		this.ingridients = ingridients;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(name).append("\n");
		for (Article a : ingridients.keySet()) {
			sb.append("\t");
			sb.append(a.getName()).append(" ");
			sb.append(ingridients.get(a)).append(a.getPrefix()).append("\n");

		}

		return sb.toString();
	}

	public String getName() {
		return name;
	}
	
	public TreeMap<Article, Double> getIngirdients() {
		return ingridients;
	}
	
}
