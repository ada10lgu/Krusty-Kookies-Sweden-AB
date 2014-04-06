package gui.orders.newOrder;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import model.Customer;
import model.Factory;

@SuppressWarnings("serial")
public class CustomerSelection extends JPanel implements Observer {

	private ListSelectionListener listener;
	private Factory f;

	public CustomerSelection(Factory f, ListSelectionListener listener) {
		setBorder(BorderFactory.createTitledBorder("Customer"));
		f.addObserver(this);
		setLayout(new BorderLayout());
		this.listener = listener;
		this.f = f;
		update(null, null);

	}

	@Override
	public void update(Observable o, Object arg) {

		removeAll();

		JList<Customer> list = new JList<>();
		DefaultListModel<Customer> listModel = new DefaultListModel<>();

		for (Customer c : f.getCustomers()) {
			listModel.addElement(c);
		}

		list = new JList<Customer>(listModel);
		list.addListSelectionListener(listener);
		add(list, BorderLayout.CENTER);

	}
}
