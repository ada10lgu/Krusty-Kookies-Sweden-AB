package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelTwo extends JPanel {
	
	public PanelTwo(){
		createPage();
	}
	
	public void createPage(){
		Container panel1 = layoutComponentLeft("Ingredients", Component.LEFT_ALIGNMENT);
	    Container panel2 = layoutComponentCenter("Amount", Component.CENTER_ALIGNMENT);

	    setLayout(new FlowLayout());
	    add(panel1);
	    add(panel2);

	}
	
	private static Container layoutComponentLeft(String title, float alignment) {
	    String labels[] = { "--", "----", "--------", "------------" };
	    
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder(title));
	    
	    BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
	    container.setLayout(layout);
	    

		DefaultListModel<String> listModel = new DefaultListModel();
		
		// Skal importera namn från databasen
		listModel.addElement("Nut Ring");
		listModel.addElement("Nut Cookie");
		listModel.addElement("Amneris");
		listModel.addElement("Tango");
		listModel.addElement("Almond Delight");
		listModel.addElement("Berliner");
		
		JList<String> list = new JList<String>(listModel);
		container.add(list);
	    return container;
	}
	private static Container layoutComponentCenter(String title, float alignment) {
	    
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder(title));
	    
	    BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
	    container.setLayout(layout);
	    
	    JTextField jt = new JTextField();
	    jt.setAlignmentX(alignment);
	    container.add(jt);
	    JButton jb = new JButton("Bake");
	    jb.setAlignmentX(alignment);
	    container.add(jb);
	    
	    return container;
	}
}
