package model;

import java.util.TreeMap;

public class Product {

	private TreeMap<Article, Double> ingridients;
	private String name;

	public Product(String name) {
		this.name = name;
		this.ingridients = null;
	}

	Product(String name, TreeMap<Article, Double> ingridients) {
		this.name = name;
		this.ingridients = ingridients;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}

	public TreeMap<Article, Double> getIngirdients() {
		return ingridients;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Product)
			return obj.hashCode() == hashCode();
		return false;
	}
	
}
