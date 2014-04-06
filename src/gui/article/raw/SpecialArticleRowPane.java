package gui.article.raw;

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
public class SpecialArticleRowPane extends JPanel implements ActionListener{

	public static final int TEXT_HEIGHT = 23;
	private JTextField nameField;
	private JTextField ammountField;
	private Factory f;
	private RawMaterialPane topPane;
	
	public SpecialArticleRowPane(Factory f) {
		this.f = f;
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(new JLabel("Ammount:"));
		ammountField = new JTextField();
		
		ammountField.setPreferredSize(new Dimension(100,TEXT_HEIGHT));
		add(ammountField);
		
		JButton button = new JButton("Add");
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(80,TEXT_HEIGHT));
		add(button);
		
		nameField = new JTextField();
		
		nameField.setPreferredSize(new Dimension(100,TEXT_HEIGHT));
		add(nameField);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String name = nameField.getText();
		try {
			int ammount;
			ammount = Integer.parseInt(ammountField.getText());
			
			f.addArticle(new Article(0,name,0,""), ammount);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Must insert number");
		}
		
	}
	
}
