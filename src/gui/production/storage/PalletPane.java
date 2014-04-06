package gui.production.storage;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PalletGroup;

@SuppressWarnings("serial")
public class PalletPane extends JPanel {

	public PalletPane(PalletGroup p) {
		setLayout(new FlowLayout());
		
		add(new JLabel(p.getPrtoduct().getName()));
		add(new JLabel(""+p.getAmmount()));
	}
	
}
