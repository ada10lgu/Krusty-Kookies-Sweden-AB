package gui.article;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Article;
import model.Factory;

@SuppressWarnings("serial")
public class RawMaterialPane extends JPanel {
	public RawMaterialPane(Factory f) {

		setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		BoxLayout layout = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
		contentPanel.setLayout(layout);
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		add(scrollPane, BorderLayout.CENTER);

		contentPanel.add(new SpecialArticleRowPane(f));
		for (Article a : f.getAllRawMaterials()) {
			contentPanel.add(new ArticleRowPane(a));
		}
	}
}