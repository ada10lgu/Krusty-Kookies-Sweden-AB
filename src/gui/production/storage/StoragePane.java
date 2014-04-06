package gui.production.storage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Factory;
import model.Pallet;

@SuppressWarnings("serial")
public class StoragePane extends JPanel implements Observer {

	JPanel content;
	Factory f;
	
	public StoragePane(Factory f) {
		this.f = f;
		f.addObserver(this);
		setLayout(new BorderLayout());
	
		setBorder(BorderFactory.createTitledBorder("In stock"));
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		this.content = content;
		update(null,null);
		JScrollPane scroll = new JScrollPane(content);
		add(scroll,BorderLayout.CENTER);
	}

	@Override
	public void update(Observable o, Object arg) {
		content.removeAll();
		ArrayList<Pallet> pallets = f.getPallets();
		for (Pallet p : pallets) {
			content.add(new PalletPane(p));
		}
	}
	
	
	
}
