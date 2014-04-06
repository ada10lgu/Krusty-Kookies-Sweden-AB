package model;

public class Pallet {
	private Product p;
	private int ammount;

	public Pallet(Product p, int ammount) {
		this.p = p;
		this.ammount = ammount;
	}

	public int getAmmount() {
		return ammount;
	}

	public Product getPrtoduct() {
		return p;
	}

}
