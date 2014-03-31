package model;

public class Article implements Comparable<Article>{
	private int id;
	private int ammount;
	private String name;
	private String prefix;

	public Article(int id, String name, int ammount, String prefix) {
		this.id = id;
		this.ammount = ammount;
		this.name = name;
		this.prefix = prefix;
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
		return "[id=" + id + ", name=" + name + ", ammount=" + ammount + prefix
				+ "]";
	}

	public String getPrefix() {
		return prefix;
	}

	@Override
	public int compareTo(Article a) {
		return name.compareTo(a.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Article)
			return ((Article) obj).name.equals(name);
		return false;
	}

}
