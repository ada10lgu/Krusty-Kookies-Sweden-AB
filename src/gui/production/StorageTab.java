package gui.production;

import gui.production.storage.BatchBlockPane;
import gui.production.storage.StoragePane;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Factory;

@SuppressWarnings("serial")
public class StorageTab extends JPanel {

	public StorageTab(Factory f) {
		setLayout(new GridLayout(1, 2));

		add(new BatchBlockPane(f), BorderLayout.WEST);
		add(new StoragePane(f), BorderLayout.EAST);
	}
}
