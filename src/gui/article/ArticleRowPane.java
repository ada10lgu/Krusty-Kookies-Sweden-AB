package gui.article;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Article;

@SuppressWarnings("serial")
public class ArticleRowPane extends JPanel implements ActionListener{

	public static final int TEXT_HEIGHT = 23;
	
	public ArticleRowPane(Article a) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JTextField textField = new JTextField();
		
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
		System.out.println("hej");
	}
	
}
