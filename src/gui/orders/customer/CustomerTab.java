package gui.orders.customer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Customer;
import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class CustomerTab extends JPanel implements Observer, ListSelectionListener {
	
	private Factory f;
	private JPanel topPanel;
	private CustomerInfoPane info;
	private JList<Customer> list;
	
	public CustomerTab(Factory f) {
		setLayout(new GridLayout(2, 1));
		this.f = f;
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		info = new CustomerInfoPane();
		add(topPanel);
		add(info);
		update(null,null);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		topPanel.removeAll();
		
		list = new JList<>();
		DefaultListModel<Customer> listModel = new DefaultListModel<>();

		for (Customer c : f.getCustomers()) {
			listModel.addElement(c);
		}

		list = new JList<Customer>(listModel);
		list.addListSelectionListener(this);
		topPanel.add(list,BorderLayout.NORTH);
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) 
			return;
		
		info.showCustomer(list.getSelectedValue());
		
	}
}
