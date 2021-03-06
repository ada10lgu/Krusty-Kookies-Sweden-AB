package gui;

import gui.article.ArticlePane;
import gui.orders.OrderPane;
import gui.production.ProductionPane;

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

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Groceries", new ArticlePane(f));
		tabbedPane.addTab("Production", new ProductionPane(f));
		tabbedPane.addTab("Orders and Deliveries",  new OrderPane(f));
		add(tabbedPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
