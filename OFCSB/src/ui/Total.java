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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.Menu;
import dao.ProductDAO;


public class Total extends JFrame implements ActionListener
{
private JPanel contentsPanel;

public boolean clear;
ProductDAO p;
JButton cash, debit,enter,back;
double debitTotal, changeNumber;
JLabel title, dollarSign, change;
List<Menu> transactionListCopy = new ArrayList<>();
JTextField amount;

	public Total()
	 {
		
		//Components of JFrame 
		 super("Total");
			JFrame popup = new JFrame();
			setResizable(false);
			setLayout(null);
			setSize(500,400);  
			
			contentsPanel= new JPanel();
			contentsPanel.setLayout(null);
			setContentPane(contentsPanel);
			contentsPanel.setBackground(new Color(255,255,51));
			
			
			clear = false;
			
			
		title =new JLabel();
		title.setText("Totaling");
		title.setBounds(200, 0, 150, 50);
		title.setFont(new Font("Georgia", Font.BOLD, 26));
		title.setForeground(new Color (0,0,0));
		contentsPanel.add(title);
		
		dollarSign =new JLabel();
		dollarSign.setText("$");
		dollarSign.setBounds(120, 115, 150, 50);
		dollarSign.setFont(new Font("Georgia", Font.BOLD, 16));
		dollarSign.setVisible(false);
		dollarSign.setForeground(new Color (0,0,0));
		contentsPanel.add(dollarSign);
		
		cash= new JButton("Cash");
		cash.setBounds(100,120, 100, 100);
		cash.addActionListener(this);
		contentsPanel.add(cash);
	
		
		debit= new JButton("Debit");
		debit.setBounds(300,120, 100, 100);
		debit.addActionListener(this);
		debit.setVisible(true);
		contentsPanel.add(debit);
		
		
		
		enter= new JButton("ENTER");
		enter.setBounds(200,200, 100, 50);
		enter.addActionListener(this);
		enter.setVisible(false);
		contentsPanel.add(enter);
		
		
		back= new JButton("BACK");
		back.setBounds(200,300, 100, 50);
		back.addActionListener(this);
		back.setVisible(false);
		contentsPanel.add(back);
		
		amount = new JTextField();
		amount.setText(""+ debitTotal);
		amount.setBounds(150,125,200,30);
		amount.setVisible(false);
		contentsPanel.add(amount);
		
		change = new JLabel ();
		change.setVisible(false);
		change.setFont(new Font("Georgia", Font.BOLD, 26));
		contentsPanel.add(change);
		
		try {
			p = new ProductDAO();
		} catch (Exception ek) {
			ek.printStackTrace();
		}
		
			
	 }
	
	public void setDebitTotal (double i)
	{
		debitTotal = i;
	}
	
	public void setTransactionListCopy (List<Menu> t)
	{
		transactionListCopy =t;
	}
	
	public List<Menu> getTransactionListCopy ()
	{
		return transactionListCopy;
		
	}
	
	public boolean getClear()
	{
		return clear;
	}
	
	public void cleared (boolean t)
	{
		if (t=true)
		{
			System.out.print("cleared");
		}
		else
		{
			System.out.print ("not cleared");
		}
	
		
	}
	
	public void backToOptionsDisplay()
	{
		cash.setVisible(true);
		debit.setVisible(true);
		title.setText("Totaling");
		amount.setVisible(false);
		dollarSign.setVisible(false);
		back.setVisible(false);
		enter.setVisible(false);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource()== debit)
		{
			debit.setVisible(false);
			cash.setVisible(false);
			back.setVisible(true);
			title.setText("Amount");
			amount.setVisible(true);
			amount.setText(""+ debitTotal);
			dollarSign.setVisible(true);
			enter.setVisible(true);
			System.out.print(clear);
			
			
		}
		
		if(e.getSource()== enter && title.getText().compareTo("Amount")==0)
		{
			title.setText("Tansaction Complete");
			debit.setVisible(false);
			cash.setVisible(false);
			back.setVisible(false);
			enter.setVisible(false);
			amount.setVisible(false);
			dollarSign.setVisible(false);
			title.setBounds(100,100, 300, 100);
			System.out.print(clear);

			
			
			for (int i = 0; i < transactionListCopy.size(); i++) 
			{
				try {
					p.decreaseInventory(transactionListCopy.get(i));
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				transactionListCopy.get(i).setSold(transactionListCopy.get(i).getSold()+1);
				
			}
		
			clear = true;
			
			
			System.out.print(clear);
			
		}
		
		if (e.getSource()== cash)
		{
			
			debit.setVisible(false);
			cash.setVisible(false);
			back.setVisible(true);
			enter.setVisible(true);
			
			title.setText("Enter Amount Given:");
			title.setBounds(100,0, 300, 100);
			
			amount.setVisible(true);
			
			dollarSign.setVisible(true);
			
			
			 
		}
		
		if(e.getSource()== enter && title.getText().compareTo("Enter Amount Given:")==0 &&( Double.parseDouble(amount.getText()))>debitTotal)
		{
			
			title.setText("Change Due:");
			debit.setVisible(false);
			cash.setVisible(false);
			back.setVisible(false);
			enter.setVisible(false);
			amount.setVisible(false);
			dollarSign.setVisible(true);
			
			changeNumber = Double.parseDouble(amount.getText());
			change.setText(""+ (changeNumber-debitTotal));
			change.setVisible(true);
			change.setBounds(150, 115, 150, 50);
			dollarSign.setFont(new Font("Georgia", Font.BOLD, 26));
			
			for (int i = 0; i < transactionListCopy.size(); i++) 
			{
				try {
					p.decreaseInventory(transactionListCopy.get(i));
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				transactionListCopy.get(i).setSold(transactionListCopy.get(i).getSold()+1);
				try {
					p.updateMenu(transactionListCopy.get(i));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(""+transactionListCopy.get(i));
				System.out.println (""+transactionListCopy.get(i).getSold());
			}
			
			clear=true;
			
		}
		
		if (e.getSource()==back)
		{
			backToOptionsDisplay();
		}
	}
	

	public static void main(String[] args)
	{
		Total b = new Total();
		b.setVisible(true);

	}
	


	
	
}


