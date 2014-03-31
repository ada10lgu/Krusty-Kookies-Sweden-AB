package gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


class TabbedPane	extends JFrame
{
	private	JTabbedPane tabbedPane;
	//private	JPanel panel1;
	private	JPanel panel2;
	private	JPanel panel3;


	public TabbedPane()
	{
	
		setTitle( "Krusty Kookies Sweden AB" );
		setSize( 600, 600 );
		setBackground( Color.gray );

		JPanel topPanel = new JPanel();
		
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages
		PanelOne p1 = new PanelOne();
		PanelTwo p2 = new PanelTwo();
		PanelThree p3 = new PanelThree();
//		p1.createPage();
//		p2.createPage();
//		p3.createPage();

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Raw Materials and Recepies", p1 );
		tabbedPane.addTab( "Production", p2 );
		tabbedPane.addTab( "Orders and Deliveries", p3);
		topPanel.add( tabbedPane, BorderLayout.CENTER );
	}




	

}