package gui.article.recepie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Article;
import model.Factory;
import model.Product;

@SuppressWarnings("serial")
public class RecepieInfo extends JPanel implements ListSelectionListener,
		Observer,ActionListener {

	private Factory f;
	private JPanel centerPanel;
	private JButton saveButton;
	private TreeMap<Article, Double> articles;
	private Product selectedProduct;
	
	public RecepieInfo(Factory f) {
		setLayout(new BorderLayout());
		this.f = f;
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(new NewCookieButton(f), BorderLayout.EAST);
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		topPanel.add(saveButton, BorderLayout.WEST);

		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(centerPanel);
		add(topPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		update(null, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		saveButton.setEnabled(false);

		centerPanel.removeAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		saveButton.setEnabled(true);

		articles = new TreeMap<>();
		for (Article a : f.getAllRawMaterials()) {
			articles.put(a, 0.0);
		}
		
		JList<Product> list = (JList<Product>) e.getSource();
		selectedProduct = list.getSelectedValue();
		articles.putAll(selectedProduct.getIngirdients());
		
		centerPanel.removeAll();
		for (Article a : articles.keySet()) {
			centerPanel.add(new ArticleField(a));
		}
		
		updateUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (selectedProduct != null) {
			f.updateProduct(selectedProduct,articles);
		}
	}
	private class ArticleField extends JPanel implements KeyListener {
		
		private Article a;
		private JTextField text;
		
		public ArticleField(Article a) {
			this.a = a;
			setLayout(new FlowLayout(FlowLayout.LEFT));
			text = new JTextField(5);
			text.setText(""+articles.get(a));
			text.addKeyListener(this);
			add(text);
			add(new JLabel(a.getName()));
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			String text = this.text.getText();
			try {
				double ammount = Double.parseDouble(text);
				articles.put(a,ammount);
			} catch (NumberFormatException e) {
				
			}
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	
}
