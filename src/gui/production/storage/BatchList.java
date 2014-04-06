package gui.production.storage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;

import model.Batch;
import model.Factory;

@SuppressWarnings("serial")
public class BatchList extends JPanel implements Observer {

	private Factory f;
	private ListSelectionListener listener;
	
	public BatchList(Factory f, ListSelectionListener listener) {
		this.f = f;
		this.listener = listener;
		f.addObserver(this);
		setLayout(new BorderLayout());

		update(null, null);

	}

	@Override
	public void update(Observable o, Object arg) {
		removeAll();
		DefaultListModel<Batch> listModel = new DefaultListModel<>();
		ArrayList<Batch> batches = f.getBatches();
		for (Batch b : batches)
			listModel.addElement(b);
		JList<Batch> list = new JList<>(listModel);
		list.addListSelectionListener(listener);
		add(list, BorderLayout.CENTER);
	}
}
