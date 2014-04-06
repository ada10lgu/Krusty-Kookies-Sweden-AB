package gui.orders.newOrder;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class ProductList extends JPanel implements Observer {

	private Factory f;
	private HashMap<Product, Integer> products;

	public ProductList(Factory f) {
		this.f = f;
		setBorder(BorderFactory.createTitledBorder("Products"));
		f.addObserver(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		update(null, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		removeAll();
		products = new HashMap<>();
		for (Product p : f.getAllProducts()) {
			products.put(p, 0);
			add(new ProductEntry(p));
		}

	}

	private class ProductEntry extends JPanel implements KeyListener {

		private JTextField text;
		private Product p;

		public ProductEntry(Product p) {
			this.p = p;
			setLayout(new BorderLayout());
			add(new JLabel(p.getName()), BorderLayout.WEST);
			text = new JTextField(5);
			add(text, BorderLayout.EAST);
			text.addKeyListener(this);

		}

		@Override
		public void keyPressed(KeyEvent arg0) {

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			String text = this.text.getText();
			int ammount = 0;
			try {
				ammount = Integer.parseInt(text);
			} catch (NumberFormatException e) {

			}
			products.put(p, ammount);
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	public HashMap<Product, Integer>  getList() {
		return products;
	}
}
