package model;


public class Batch {
	private Product p;
	private String date;
	private String status;
	
	public Batch(Product p, String date, String status) {
		this.p = p;
		this.date = date;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return p.getName() + ": " + date + " <" + status + ">";
	}
}
