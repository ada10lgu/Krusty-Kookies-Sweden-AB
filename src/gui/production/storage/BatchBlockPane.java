package gui.production.storage;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Factory;

@SuppressWarnings("serial")
public class BatchBlockPane extends JPanel {

	public BatchBlockPane(Factory f) {
		setLayout(new BorderLayout());
		
		add(new JTextArea("fds"),BorderLayout.CENTER);
	}
	
}
