package gui.orders;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class OrderPane extends JPanel {
	private static JList<String> customerList;

	public OrderPane(){
		createPage();
	}
	
	public void createPage(){
		JPanel orderPanel = new JPanel();
		//orderPanel.setLayout(new GridLayout());
		JPanel deliveryPanel = new JPanel();
		JTabbedPane tp = new JTabbedPane();
		
		tp.addTab("Orders", orderPanel);
		tp.addTab("Deliveries", deliveryPanel);
		add(tp, BorderLayout.CENTER);

		Container panel1 = layoutComponentLeft("Products", Component.LEFT_ALIGNMENT);
//	    Container panel2 = layoutComponentCenter("Order Amount", Component.CENTER_ALIGNMENT);

	    setLayout(new FlowLayout());
	    orderPanel.add(panel1);
//	    orderPanel.add(panel2);
		

	}
	private static Container layoutComponentLeft(String title, float alignment) {
	    
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder(title));
	    
	    BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
	    container.setLayout(layout);
	   
	    JPanel panelInPane = new JPanel();
	    panelInPane.setLayout(new GridLayout(6,2));
	    JScrollPane jScrollPane = new JScrollPane(panelInPane);
	    
	   
		// Skal importera namn från databasen
		String str[] = {"Nut Ring", "Nut Cookie", "Amneris", "Tango", "Almond Delight", "Berliner"};
		for(int i = 0; i< str.length; i++){
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());
			JLabel jl = new JLabel(str[i]);
			JTextField jt = new JTextField();
			jt.setPreferredSize(new Dimension(90,18));
			jl.setPreferredSize(new Dimension(90, 18));
			jp.add(jl,BorderLayout.WEST);
			jp.add(jt,BorderLayout.EAST);
			panelInPane.add(jp);
			
			
		}
		container.add(jScrollPane);
		
		return container;
	}

	private static Container layoutComponentCenter(String title, float alignment) {
	    
	    JPanel container = new JPanel();
	    container.setBorder(BorderFactory.createTitledBorder(title));
	    
	    BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
	    container.setLayout(layout);
		
		JLabel jl = new JLabel("Products");
		container.add(jl);

		for(int i= 0; i< 6 ; i++){
			JTextField jt = new JTextField();
			container.add(jt);
		}
	    return container;
	}

	private static Container layoutComponentEast(String title, float alignment) {
	    
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

	class NameSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (customerList.isSelectionEmpty()) {
				return;
			}
			String customer = customerList.getSelectedValue();

		}
	}
}
