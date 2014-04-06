package gui.orders.print;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
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
		ListSelectionListener, ActionListener {

	private Factory f;
	private JTextArea text;
	private JButton button;
	private Order o;
	
	public OrderInfo(Factory f) {
		this.f = f;
		f.addObserver(this);
		text = new JTextArea();
		button = new JButton("Deliver");
		button.setEnabled(false);
		button.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(text);
		setLayout(new BorderLayout());
		add(scrollPane,BorderLayout.CENTER);
		add(button,BorderLayout.NORTH);
		text.setEditable(false);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		text.setText("");
		button.setEnabled(false);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		@SuppressWarnings("unchecked")
		JList<Order> list = (JList<Order>) e.getSource();
		
		model.OrderInfo info = f.getOrderInfo(list.getSelectedValue());
		text.setText(info.toString());
		o = info.getOrder();
		button.setEnabled(info.isPending());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		f.sendOrder(o);
	}
}