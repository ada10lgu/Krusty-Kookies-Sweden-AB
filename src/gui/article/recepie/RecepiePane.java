package gui.article.recepie;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Factory;

@SuppressWarnings("serial")
public class RecepiePane extends JPanel{
	public RecepiePane(Factory f) {
		setLayout(new BorderLayout());
		
		RecepieInfo info = new RecepieInfo(f);
		
		add(new RecepieList(f, info),BorderLayout.WEST);
		add(info,BorderLayout.CENTER);
		
	}

}
