package gui.article;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Article;
import model.Factory;

@SuppressWarnings("serial")
public class ArticleRowPane extends JPanel implements ActionListener{

	public static final int TEXT_HEIGHT = 23;
	
	private Article a;
	private Factory f;
	private JTextField textField;
	
	
	public ArticleRowPane(Factory f, Article a) {
		this.f = f;
		this.a = a;
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JLabel("Ammount:"));
		textField = new JTextField();
		
		textField.setPreferredSize(new Dimension(100,TEXT_HEIGHT));
		add(textField);
		
		JButton button = new JButton("Add");
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(80,TEXT_HEIGHT));
		add(button);
		
		add(new JLabel(a.getName()));
		add(new JLabel("" + a.getAmmount() + a.getPrefix()));
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			int ammount = Integer.parseInt(textField.getText());
			f.addArticle(a, ammount);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Must insert number");
		}
	}
	
}
