package gui.production.production;

import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class ProductList extends JPanel implements ListSelectionListener {

	private JList<String> list;
	private HashMap<String, Product> products;
	private ProducePane info;

	public ProductList(Factory f, ProducePane info) {
		this.info = info;
		products = new HashMap<>();
		setBorder(BorderFactory.createTitledBorder("Products"));
		DefaultListModel<String> listModel = new DefaultListModel<>();

		for (Product p : f.getAllProducts()) {
			listModel.addElement(p.getName());
			products.put(p.getName(), p);
		}

		list = new JList<String>(listModel);
		list.addListSelectionListener(this);
		add(list);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			String name = list.getSelectedValue();
			info.updateData(products.get(name));
		}
	}

}
