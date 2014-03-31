package model;

public class Article {
	private int id;
	private int ammount;
	private String name;

	public Article(int id, String name, int ammount) {
		this.id = id;
		this.ammount = ammount;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getAmmount() {
		return ammount;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "[id="+ id + ", name=" + name + ", ammount=" + ammount + "]";
	}
	
}
