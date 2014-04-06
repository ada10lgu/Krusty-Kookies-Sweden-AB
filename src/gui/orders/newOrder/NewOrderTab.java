package gui.orders.newOrder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Customer;
import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class NewOrderTab extends JPanel implements ListSelectionListener,
		ActionListener, Observer {

	private Customer c;
	private ProductList list;
	private Factory f;

	public NewOrderTab(Factory f) {
		this.f = f;
		setLayout(new BorderLayout());
		JPanel top = new JPanel();

		list = new ProductList(f);
		top.add(list);
		top.add(new CustomerSelection(f, this));
		f.addObserver(this);

		JButton button = new JButton("Place order");
		button.addActionListener(this);

		add(top, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		JList<Customer> list = (JList<Customer>) e.getSource();

		c = list.getSelectedValue();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		c = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (c == null) {
			popUp("Select customer!");
			return;
		}

		int totalAmmount = 0;
		for (Product p : list.getList().keySet()) {
			totalAmmount += list.getList().get(p);
		}

		if (totalAmmount == 0) {
			popUp("The order does not have any products!");
			return;
		}

		int order = f.placeOrder(c, list.getList());

		if (order == 0) {
			popUp("Not enough products in stock, can't place order!");
			return;
		}
		
		popUp("Order #"+order+" placed!");
	}

	private void popUp(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
