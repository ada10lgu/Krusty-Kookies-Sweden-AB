package gui.article.raw;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Article;
import model.Factory;

@SuppressWarnings("serial")
public class RawMaterialPane extends JPanel implements Observer {
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
		update(null, null);
		f.addObserver(this);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		contentPanel.removeAll();
		contentPanel.add(new SpecialArticleRowPane(f));
		for (Article a : f.getAllRawMaterials()) {
			contentPanel.add(new ArticleRowPane(f,a));
		}		
	}
}