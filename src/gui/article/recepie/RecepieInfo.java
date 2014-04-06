package gui.article.recepie;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Factory;

@SuppressWarnings("serial")
public class RecepieInfo extends JPanel implements ListSelectionListener, Observer{

	private Factory f;
	private JPanel centerPanel;
	
	public RecepieInfo(Factory f) {
		this.f = f;
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(new NewCookieButton(f),BorderLayout.EAST);
		
		centerPanel = new JPanel();
		
		add(topPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
