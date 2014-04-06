package gui.production;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Factory;

@SuppressWarnings("serial")
public class ProductionPane extends JPanel {

	public ProductionPane(Factory f) {
		setLayout(new BorderLayout());
		JTabbedPane pane = new JTabbedPane();
		
		pane.addTab("Production", new ProductionTab(f));
		pane.addTab("Storage", new StorageTab(f));
		add(pane,BorderLayout.CENTER);
	}

}
