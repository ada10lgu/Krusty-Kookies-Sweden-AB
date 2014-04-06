package gui.orders.print;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Factory;
import model.Order;

public class OrderInfo extends JTextArea implements Observer,
		ListSelectionListener {

	private Factory f;
	
	public OrderInfo(Factory f) {
		this.f = f;
		setEditable(false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setText("");

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		@SuppressWarnings("unchecked")
		JList<Order> list = (JList<Order>) e.getSource();
		
		model.OrderInfo info = f.getOrderInfo(list.getSelectedValue());
		setText(info.toString());
	}
}