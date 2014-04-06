package gui.production.storage;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Factory;
import model.Pallet;

@SuppressWarnings("serial")
public class StoragePane extends JPanel {

	public StoragePane(Factory f) {
		setLayout(new BorderLayout());
		ArrayList<Pallet> pallets = f.getPallets();
	
		setBorder(BorderFactory.createTitledBorder("In stock"));
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		for (Pallet p : pallets) {
			content.add(new PalletPane(p));
		}
		
		JScrollPane scroll = new JScrollPane(content);
		add(scroll,BorderLayout.CENTER);
		
	}
	
}
