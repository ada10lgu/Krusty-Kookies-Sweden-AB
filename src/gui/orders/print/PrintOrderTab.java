package gui.orders.print;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Factory;

@SuppressWarnings("serial")
public class PrintOrderTab extends JPanel implements ListSelectionListener {

	public PrintOrderTab(Factory f) {
		setLayout(new GridLayout(1, 2));

		OrderInfo info = new OrderInfo(f);
		add(new OrderList(f, info));
		add(info);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}
}
