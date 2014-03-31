package gui;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class PanelOne extends JPanel {
	
	public PanelOne(){
		createPage();
	}
	
	public void createPage()
	{
		
		//panel = new JPanel();
		
		JPanel rawMaterialsPane = new JPanel();
		JPanel materialsWest = new JPanel(new GridLayout(2,1));
		JPanel materialsEast = new JPanel();
		rawMaterialsPane.add(materialsWest, BorderLayout.WEST);
		rawMaterialsPane.add(materialsEast, BorderLayout.EAST);
		
		JPanel recepiesPane = new JPanel();
		
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Raw Materials", rawMaterialsPane);
		tp.addTab("Recepies", recepiesPane);
		add(tp, BorderLayout.WEST);
		
		JLabel ingr = new JLabel("Ingredients");
		materialsWest.add(ingr);

		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Sugar");
		JList list = new JList(listModel);
		listModel.addElement("Chocklate");
		listModel.addElement("Chocklate");
		listModel.addElement("Chocklate");
		listModel.addElement("Chocklate");
		listModel.addElement("Chocklate");
		listModel.addElement("Chocklate");
		listModel.addElement("Chocklate");
		materialsWest.add(list);
//		panel1.setLayout( null );
		
//		JLabel label1 = new JLabel( "Test1:" );
//		label1.setBounds( 10, 15, 150, 20 );
//		panel1.add( label1 );
//
//		JTextField field = new JTextField();
//		field.setBounds( 10, 35, 150, 20 );
//		panel1.add( field );
//
//		JLabel label2 = new JLabel( "Test2:" );
//		label2.setBounds( 10, 60, 150, 20 );
//		panel1.add( label2 );
//
//		JPasswordField fieldPass = new JPasswordField();
//		fieldPass.setBounds( 10, 80, 150, 20 );
//		panel1.add( fieldPass );
	}

}
