package gui.production.storage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Batch;
import model.Factory;

@SuppressWarnings("serial")
public class BatchBlockPane extends JPanel implements ActionListener, ListSelectionListener,Observer {

	private JButton button;
	private Batch batch;
	private Factory f;
	
	public BatchBlockPane(Factory f) {
		this.f = f;
		f.addObserver(this);
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		button = new JButton("Select a batch");
		button.addActionListener(this);
		topPanel.add(button);
		
		add(topPanel,BorderLayout.NORTH);
		add(new BatchList(f,this),BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (batch != null) {
			f.changeBatch(batch);
		}
		batch = null;
		update(null,null);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) 
			return;
		@SuppressWarnings("unchecked")
		JList<Batch> list = (JList<Batch>) e.getSource();

		batch = list.getSelectedValue();
		button.setText(batch.getNegatedStatus());
	}

	@Override
	public void update(Observable o, Object arg) {
		button.setText("Select a batch");
	}
	
}
