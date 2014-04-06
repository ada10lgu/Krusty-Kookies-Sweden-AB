package gui.orders;
import gui.orders.customer.CustomerTab;
import gui.orders.newOrder.NewOrderTab;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Factory;




@SuppressWarnings("serial")
public class OrderPane extends JPanel {

	public OrderPane(Factory f){
		setLayout(new BorderLayout());
		
		JTabbedPane tabbed = new JTabbedPane();
		tabbed.addTab("New Order", new NewOrderTab(f));
		tabbed.addTab("Print order", new PrintOrderTab(f));
		tabbed.addTab("Curstomers", new CustomerTab(f));
		
		add(tabbed,BorderLayout.CENTER);
		
		
	}
	
}
