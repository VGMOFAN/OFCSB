package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProductDAO;


public class EditPrice extends JFrame implements ActionListener
{
private JPanel contentsPanel;

JComboBox<String> comboBox;
JButton enter, enter2;
JLabel title;
JTextField amount;
int item;
double changedPrice;

List <String> itemNames= new ArrayList<>();;

ProductDAO p;

	

	public EditPrice()
	 {
		//Components of JFrame 
		 super("Edit Price");
			JFrame popup = new JFrame();
			setResizable(false);
			setLayout(null);
			setSize(500,400);  
			
			contentsPanel= new JPanel();
			setContentPane(contentsPanel);
			contentsPanel.setLayout(null);
			contentsPanel.setBackground(new Color(255,255,51));
			
	
		

			title = new JLabel ("What Product:");
			title.setBounds(150, 0, 200, 50);
			title.setFont(new Font("Georgia", Font.BOLD, 26));
			title.setForeground(new Color (0,0,0));
			contentsPanel.add(title);
			
			enter= new JButton("ENTER");
			enter.setBounds(200,200, 100, 50);
			enter.addActionListener(this);
			enter.setVisible(true);
			enter.addActionListener(this);
			contentsPanel.add(enter);
			
			amount = new JTextField();
			amount.setText("");
			amount.setBounds(150,125,200,30);
			amount.setVisible(false);
			contentsPanel.add(amount);
			
			enter2= new JButton("ENTER");
			enter2.setBounds(200,200, 100, 50);
			enter2.addActionListener(this);
			enter2.setVisible(false);
			enter2.addActionListener(this);
			contentsPanel.add(enter2);
		
		
		
comboBox= new JComboBox <String>();
comboBox.setBounds(150, 125, 200, 30);
comboBox.setVisible(true);
contentsPanel.add(comboBox);
		

		
		try {
			p = new ProductDAO();
		} catch (Exception el) {
			el.printStackTrace();
		}
		
	try {
		for (int i =0; i<p.getAllMenus().size();i++)
		{
			itemNames.add(p.getAllMenus().get(i).getName());
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	for(int i =0; i< itemNames.size();i++)
	{
		comboBox.addItem(itemNames.get(i));
	}
	
			
	 }
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== enter && title.getText().compareTo("What Product:")==0)
		{
			item = comboBox.getSelectedIndex();
			title.setText("Changed Price:");
			title.setBounds(150, 0, 250, 50);
			comboBox.setVisible(false);
			enter2.setBounds(200,250, 100, 50);
			enter2.setVisible(true);
			enter.setVisible(false);
			amount.setVisible(true);
			changedPrice = Double.parseDouble(amount.getText());
		}
		
		if(e.getSource()== enter2 && title.getText().compareTo("Changed Price:")==0)
		{
			title.setText("Complete");
			amount.setText("");
			title.setBounds(165, 125, 250, 50);
			enter2.setVisible(false);
			amount.setVisible(false);
			try {
				p.getAllMenus().get(item).setPrice(changedPrice);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//Ensure to add the records
		
	}
	
	public static void main(String[] args)
	{
		EditPrice b = new EditPrice();
		b.setVisible(true);

	}
}

