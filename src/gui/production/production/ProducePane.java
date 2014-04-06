package gui.production.production;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class ProducePane extends JPanel implements ActionListener {

	private InfoPanel info;
	private Product p;
	private JTextField ammountPanel;
	private Factory f;

	public ProducePane(Factory f) {
		this.f = f;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Produce"));

		info = new InfoPanel(f);
		add(info, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		ammountPanel = new JTextField(5);
		buttonPanel.add(ammountPanel);
		JButton button = new JButton("Bake");
		button.addActionListener(this);
		buttonPanel.add(button);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void updateData(Product product) {
		this.p = product;
		info.updateData(product);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (p == null) {
			JOptionPane.showMessageDialog(null, "Must select a product first.");
		} else {
			try {
				int ammount = Integer.parseInt(ammountPanel.getText());
				if (f.produceProduct(p, ammount)) {
					JOptionPane.showMessageDialog(null, "Baking compleate!");
					ammountPanel.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "We did not have the right ammount of ingredients in stock.");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Insert a correct number.");
			}
		}
		
	}

}
