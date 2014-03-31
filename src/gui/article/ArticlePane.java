package gui.article;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import model.Factory;

@SuppressWarnings("serial")
public class ArticlePane extends JPanel {

	public ArticlePane(Factory f) {
		setLayout(new BorderLayout());
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Raw Materials", new RawMaterialPane(f));
		tp.addTab("Recepies", new ProductPane());
		add(tp, BorderLayout.CENTER);
	}


}
