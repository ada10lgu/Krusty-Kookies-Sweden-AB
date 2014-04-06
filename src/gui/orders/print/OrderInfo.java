package gui.orders.print;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Factory;
import model.Order;

@SuppressWarnings("serial")
public class OrderInfo extends JPanel implements Observer,
		ListSelectionListener {

	private Factory f;
	private JTextArea text;
	
	
	public OrderInfo(Factory f) {
		this.f = f;
		f.addObserver(this);
		text = new JTextArea(31,20);
		
		JScrollPane scrollPane = new JScrollPane(text);
		setLayout(new BorderLayout());
		add(scrollPane,BorderLayout.NORTH);
		text.setEditable(false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		text.setText("");

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		@SuppressWarnings("unchecked")
		JList<Order> list = (JList<Order>) e.getSource();
		
		model.OrderInfo info = f.getOrderInfo(list.getSelectedValue());
		text.setText(info.toString());
	}
}