package gui.production;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Factory;

@SuppressWarnings("serial")
public class ProductionPane extends JPanel {

	public ProductionPane(Factory f) {
		setLayout(new BorderLayout());
		ProducePane produce = new ProducePane(f);
		add(new ProductList(f, produce), BorderLayout.WEST);
		add(produce, BorderLayout.CENTER);
	}

}
