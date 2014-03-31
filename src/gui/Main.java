package gui;

import gui.article.ArticlePane;
import gui.orders.PanelThree;
import gui.production.PanelTwo;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import model.Factory;

@SuppressWarnings("serial")
public class Main extends JFrame {
	public static void main(String[] args) {
		Factory f = new Factory(Factory.DATABASE_CONNECTION);
		new Main(f);
	}

	public Main(Factory f) {
		setTitle("Krusty Kookies Sweden AB");
		setSize(600, 600);
		setBackground(Color.gray);

		// Create the tab pages
		ArticlePane p1 = new ArticlePane(f);
		PanelTwo p2 = new PanelTwo();
		PanelThree p3 = new PanelThree();

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Raw Materials and Recepies", p1);
		tabbedPane.addTab("Production", p2);
		tabbedPane.addTab("Orders and Deliveries", p3);
		add(tabbedPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
