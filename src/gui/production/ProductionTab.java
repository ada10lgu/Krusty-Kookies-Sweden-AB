package gui.production;

import gui.production.production.ProducePane;
import gui.production.production.ProductList;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Factory;

@SuppressWarnings("serial")
public class ProductionTab extends JPanel {

	public ProductionTab(Factory f) {
		setLayout(new BorderLayout());
		ProducePane produce = new ProducePane(f);
		add(new ProductList(f, produce), BorderLayout.WEST);
		add(produce, BorderLayout.CENTER);
	}
}
