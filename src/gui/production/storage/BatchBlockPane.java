package gui.production.storage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Factory;

@SuppressWarnings("serial")
public class BatchBlockPane extends JPanel implements ActionListener {

	JButton button;
	
	public BatchBlockPane(Factory f) {
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		button = new JButton("hej");
		button.addActionListener(this);
		topPanel.add(button);
		
		add(topPanel,BorderLayout.NORTH);
		add(new BatchList(f),BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("hej");
		
	}
	
}
