package gui;

import gui.article.RawMaterialPane;
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

		// Create the tab pages
		ProductionPane p2 = new ProductionPane();
		OrderPane p3 = new OrderPane();

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Raw materials", new RawMaterialPane(f));
		tabbedPane.addTab("Production", p2);
		tabbedPane.addTab("Orders and Deliveries", p3);
		add(tabbedPane, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
