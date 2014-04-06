package model;

public class PalletGroup {
	private Product p;
	private int ammount;

	public PalletGroup(Product p, int ammount) {
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
