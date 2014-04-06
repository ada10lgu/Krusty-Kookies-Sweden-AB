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
	
	public String getNegatedStatus() {
		if (status.equals("available"))
			return "Block";
		return "Unblock";
	}

	String getProduct() {
		return p.getName();
	}
	String getDate() {
		return date;
	}
	String getStatus() {
		return status;
	}
}
