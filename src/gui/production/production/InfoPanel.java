package gui.production.production;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Article;
import model.Factory;
import model.Product;

public class InfoPanel extends JPanel {

	private JLabel label;
	
	public InfoPanel(Factory f) {
		setLayout(new BorderLayout());
		label = new JLabel("Press an recepie on the left to inspect");
		add(label,BorderLayout.CENTER);
	}

	public void updateData(Product p) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(p.getName()).append(":<br>");
		for (Article a : p.getIngirdients().keySet()) {
			sb.append(a.getName()).append("<br>");
		}
		sb.append("</html>");
		
		label.setText(sb.toString());
		
	}
	
}
