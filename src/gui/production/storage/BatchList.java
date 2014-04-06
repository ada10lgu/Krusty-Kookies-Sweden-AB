package gui.production.storage;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Batch;
import model.Factory;

@SuppressWarnings("serial")
public class BatchList extends JPanel implements Observer {

	public BatchList(Factory f) {
		f.addObserver(this);
		setLayout(new BorderLayout());

		DefaultListModel<Batch> listModel = new DefaultListModel<>();
		ArrayList<Batch> batches = f.getBatches();
		for (Batch b : batches)
			listModel.addElement(b);
		JList<Batch> list = new JList<>(listModel);
		add(list, BorderLayout.CENTER);
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
