package gui.article;

import gui.article.raw.RawMaterialPane;
import gui.article.recepie.RecepiePane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Factory;

@SuppressWarnings("serial")
public class ArticlePane extends JPanel {

	public ArticlePane(Factory f) {
		setLayout(new BorderLayout());

		JTabbedPane tabbed = new JTabbedPane();
		tabbed.addTab("Storage", new RawMaterialPane(f));
		tabbed.addTab("Recipies", new RecepiePane(f));
		add(tabbed, BorderLayout.CENTER);

	}
}
