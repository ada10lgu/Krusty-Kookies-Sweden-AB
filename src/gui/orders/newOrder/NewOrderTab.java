package gui.orders.newOrder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Customer;
import model.Factory;

@SuppressWarnings("serial")
public class NewOrderTab extends JPanel implements ListSelectionListener,
		ActionListener, Observer {

	private Customer c;
	private ProductList list;

	public NewOrderTab(Factory f) {
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
		System.out.println(c);
		System.out.println(list.getList());
	}

}
