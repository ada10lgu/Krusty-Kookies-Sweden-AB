package gui.article.recepie;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class RecepieList extends JPanel implements Observer  {
	
	private Factory f;
	private ListSelectionListener listener;
	
	public RecepieList(Factory f, ListSelectionListener listener) {
		this.f = f;
		f.addObserver(this);
		this.listener = listener;
		setLayout(new BorderLayout());
		update(null,null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		removeAll();

		JList<Product> list = new JList<>();
		DefaultListModel<Product> listModel = new DefaultListModel<>();

		for (Product p : f.getAllProducts()) {
			listModel.addElement(p);
		}

		list = new JList<Product>(listModel);
		list.addListSelectionListener(listener);
		add(list, BorderLayout.CENTER);
	}
	
}
