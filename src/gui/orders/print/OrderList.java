package gui.orders.print;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import model.Factory;
import model.Order;



@SuppressWarnings("serial")
public class OrderList extends JPanel implements Observer {

	private Factory f;
	private ListSelectionListener listener;
	
	public OrderList(Factory f,ListSelectionListener listener) {
		this.f = f;
		this.listener = listener;
		setLayout(new BorderLayout());
		update(null,null);
	}
	
	
	@Override
	public void update(Observable ob, Object arg) {
		removeAll();
		
		DefaultListModel<Order> listModel = new DefaultListModel<>();

		for (Order o : f.getOrders()) {
			listModel.addElement(o);
		}

		JList<Order> list = new JList<>(listModel);
		list.addListSelectionListener(listener);
		add(list,BorderLayout.CENTER);
	}

}
