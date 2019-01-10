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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Record;
import dao.ProductDAO;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DeleteFromInventory extends JFrame implements ActionListener
{

	private JPanel contentsPanel;
	
	ProductDAO p;
	Record r;
	JTextField text, text2;
	JButton enter, enter2;
	JLabel title ;
	String reason,stringDate;
	int item, numberOfItems;
	List <String> itemNames= new ArrayList<>();
	JComboBox <String> combobox;	

	public DeleteFromInventory()
	 {
		//Components of JFrame 
		 super("Delete From Inventory");
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
		
		text = new JTextField();
		text.setBounds(150,125,200,30);
		text.setVisible(false);
		contentsPanel.add(text);
		
		text2 = new JTextField();
		text2.setBounds(150,125,200,30);
		text2.setVisible(false);
		contentsPanel.add(text2);
		
		enter= new JButton("ENTER");
		enter.setBounds(200,200, 100, 50);
		enter.addActionListener(this);
		enter.setVisible(true);
		enter.addActionListener(this);
		contentsPanel.add(enter);
		
		enter2= new JButton("ENTER");
		enter2.setBounds(200,200, 100, 50);
		enter2.addActionListener(this);
		enter2.setVisible(false);
		enter2.addActionListener(this);
		contentsPanel.add(enter2);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		stringDate = formatter.format(date);
		System.out.println(stringDate);
	
		setVisible(true);
		
		combobox= new JComboBox <String>();
		
		combobox.setBounds(150, 125, 200, 30);
		combobox.setVisible(true);
		contentsPanel.add(combobox);
		
		try {
			p = new ProductDAO();
		} catch (Exception el) {
			el.printStackTrace();
		}
		
	
		try {
			for (int i =0; i<p.getAllSupplies().size();i++)
			{
				itemNames.add(p.getAllSupplies().get(i).getName());
			}
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	for(int i =0; i< itemNames.size();i++)
	{
		combobox.addItem(itemNames.get(i));
	}
	

	
	
	
	 }
	
	public static void main(String[] args)
	{
		DeleteFromInventory b = new DeleteFromInventory();
		b.setVisible(true);

	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== enter && title.getText().compareTo("What Product:")==0)
		{
			item =combobox.getSelectedIndex();
			System.out.print("" +item);
			
			title.setText("Reasoning:");
			title.setBounds(165, 0, 200, 50);
			
			text.setBounds(80,70,340,150);
			text.setVisible(true);
			
			combobox.setVisible(false);
			
			enter2.setBounds(200,250, 100, 50);
			enter2.setVisible(true);
			enter.setVisible(false);
			
			
			
			
		}

		if(e.getSource()== enter2 && title.getText().compareTo("Reasoning:")==0)
		{
			reason =text.getText();

			System.out.print (reason);
			
			title.setText("How many Items?:");
			title.setBounds(125, 0, 260, 50);
			
			text2.setBounds(150,125,200,30);
			
			enter.setBounds(200,200, 100, 50);
			enter2.setVisible(false);
			enter.setVisible(true);
			
			text.setVisible(false);
			text2.setVisible(true);
			
			
			
		}
		
		if(e.getSource()== enter && title.getText().compareTo("How many Items?:")==0 && Integer.parseInt(text2.getText())>0)
		{
			numberOfItems = Integer.parseInt(text2.getText());
			System.out.print ("hi:"+ numberOfItems);
			
			title.setText("Complete");
			title.setBounds(165, 125, 250, 50);
			enter.setVisible(false);
			text.setVisible(false);
			text2.setVisible(false);
			
			
			
	
				try {
					r = new Record( p.getAllSupplies().get(item).getID(),"Delete Inventory" ,stringDate,p.getAllSupplies().get(item).getName(), reason,numberOfItems);
					p.addRecord(r);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				

			
		}
		
	}

	
}
