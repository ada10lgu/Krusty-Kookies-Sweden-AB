package gui.article.recepie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Factory;

@SuppressWarnings("serial")
public class NewCookieButton extends JButton implements ActionListener {
	
	private Factory f;
	public NewCookieButton(Factory f) {
		super("Create new Recepie");
		this.f = f;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String name = JOptionPane.showInputDialog("HejS");
		if (name != null) {
			f.addRecepie(name);
		}
	}
}
