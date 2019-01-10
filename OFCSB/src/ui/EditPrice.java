package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.Menu;
import core.Record;
import dao.ProductDAO;


public class EditPrice extends JFrame implements ActionListener
{
private JPanel contentsPanel;

boolean display;
String editPriceString;
JComboBox<String> comboBox;
JButton enter, enter2;
JLabel title;
String stringDate;
List<Menu> transactionListDuplicate = new ArrayList<>();
JTextField amount,text2;
JTextArea duplicateArea2;
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
			
	
		
			//This is the title at the top explains what to do
			title = new JLabel ("What Product:");
			title.setBounds(150, 0, 200, 50);
			title.setFont(new Font("Georgia", Font.BOLD, 26));
			title.setForeground(new Color (0,0,0));
			contentsPanel.add(title);
			
			text2 = new JTextField();
			text2.setText("");
			text2.setBounds(150,125,200,30);
			text2.setVisible(false);
			contentsPanel.add(text2);
			
			display=false;
			
			//Enter when done putting input
			enter= new JButton("ENTER");
			enter.setBounds(200,200, 100, 50);
			enter.addActionListener(this);
			enter.setVisible(true);
			enter.addActionListener(this);
			contentsPanel.add(enter);
			
			//The input area
			amount = new JTextField();
			amount.setText("");
			amount.setBounds(150,125,200,30);
			amount.setVisible(false);
			contentsPanel.add(amount);
			
			//Another enter button when user done putting input
			enter2= new JButton("ENTER");
			enter2.setBounds(200,200, 100, 50);
			enter2.addActionListener(this);
			enter2.setVisible(false);
			enter2.addActionListener(this);
			contentsPanel.add(enter2);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			stringDate = formatter.format(date);
		
		
		
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
			comboBox.setVisible(false);
			System.out.print("" +item);
			
			title.setText("Changed Price:");
			title.setBounds(150, 0, 250, 50);
			
		
			enter2.setBounds(200,250, 100, 50);
			enter2.setVisible(true);
			enter.setVisible(false);
			
			amount.setVisible(true);
		
		}
		
		if(e.getSource()== enter2 && title.getText().compareTo("Changed Price:")==0&&  Double.parseDouble(amount.getText())>0)
		{
			changedPrice = Double.parseDouble(amount.getText());
			title.setText("Complete");
			title.setBounds(165, 125, 250, 50);
			enter2.setVisible(false);
			amount.setVisible(false);
			try {
				editPriceString = ("\n\n    ***" +  p.getAllMenus().get(item).getName()
						+ "                                $ " + changedPrice);
				System.out.print(editPriceString);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
		
		
		try {
			Record r = new Record( p.getAllMenus().get(item).getID(),"Edit Price",stringDate,p.getAllMenus().get(item).getName(), changedPrice,p.getAllMenus().get(item).getPrice());
			p.addRecord(r);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		display=true;
	}

	public String getEditPriceString ()
	{
	
		return editPriceString;
	}
	
	
	public double newTotal (double t)
	{
		double totaling;
		totaling= t + changedPrice; 
		return totaling;
	}

	public JButton getEnter2()
	{
		return enter2;
	}
	
	

	
	public static void main(String[] args)
	{
		EditPrice b = new EditPrice();
		b.setVisible(true);

	}
}


