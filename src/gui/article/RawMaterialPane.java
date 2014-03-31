package gui.article;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Article;
import model.Factory;

@SuppressWarnings("serial")
public class RawMaterialPane extends JPanel {
	private JPanel contentPanel;
	private Factory f;

	public RawMaterialPane(Factory f) {
		this.f = f;
		setLayout(new BorderLayout());
		contentPanel = new JPanel();
		BoxLayout layout = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
		contentPanel.setLayout(layout);
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		add(scrollPane, BorderLayout.CENTER);
		updateList();
	}

	public void updateList() {
		contentPanel.removeAll();
		contentPanel.add(new SpecialArticleRowPane(f,this));
		for (Article a : f.getAllRawMaterials()) {
			contentPanel.add(new ArticleRowPane(f,this,a));
		}
	}
}