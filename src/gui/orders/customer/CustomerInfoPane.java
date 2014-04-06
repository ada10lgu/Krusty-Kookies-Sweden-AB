package gui.orders.customer;

import javax.swing.JLabel;

import model.Customer;

@SuppressWarnings("serial")
public class CustomerInfoPane extends JLabel {

	public void showCustomer(Customer c) {
		StringBuilder sb = new StringBuilder();

		sb.append("<HTML>");
		sb.append("Customer id: ").append(c.getId()).append("<br>");
		sb.append("Name: ").append(c.getName()).append("<br>");
		sb.append("Address: ").append(c.getAddress()).append("<br>");

		sb.append("</HTML>");
		setText(sb.toString());
	}

}
