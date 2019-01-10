package ui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import core.Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TransactionVoid extends JFrame implements ActionListener
{
	JTextArea duplicateArea;
	JTextArea newArea;
	JButton yes, no;
	int determine;
	double resetTotal;
	List<Menu> transactionListCopy = new ArrayList<>();
	
public JPanel contentsPanel;
	

	public TransactionVoid()
	 {
		
	super("Transaction Void");
	JFrame popup = new JFrame();
	setResizable(false);
	setLayout(null);
	setSize(500,400);  
	
	contentsPanel= new JPanel();
	contentsPanel.setLayout(null);
	setContentPane(contentsPanel);
	contentsPanel.setBackground(new Color(255,255,51));
	
JLabel title = new JLabel ("Are You Sure you Want ");
title.setBounds(85, 0, getWidth(), 100);
title.setFont(new Font("Georgia", Font.BOLD, 26));
title.setForeground(new Color (0,0,0));
contentsPanel.add(title);

JLabel title2 = new JLabel ("Void the Transaction? ");
title2.setBounds(90, 30, getWidth(), 100);
title2.setFont(new Font("Georgia", Font.BOLD, 26));
title2.setForeground(new Color (0,0,0));
contentsPanel.add(title2);

resetTotal=0;


yes= new JButton("YES");


yes.setBounds(100,120, 100, 100);
yes.addActionListener(this);
contentsPanel.add(yes);

no= new JButton("NO");
no.setBounds(280,120, 100, 100);
no.addActionListener(this);
contentsPanel.add(no);



setVisible(true);
		
	
}
	
	public void setTransactionListCopyTV (List<Menu> t)
	{
		transactionListCopy =t;
	}
	
	public List<Menu> getTransactionListCopyTV ()
	{
		return transactionListCopy;
		
	}

public void actionPerformed(ActionEvent e)
{


	if (e.getSource() == yes)
	{
		determine = 1;
		
	}
	
	if (e.getSource()==no)
	{
		determine = 0;
		
	}
	
	if (determine==1)
	{
	duplicateArea.setText("");
	newArea.setText("");


	
	}
}

public void setJTextArea (JTextArea a)
{
	duplicateArea =a;
}

public void setJTextArea2 (JTextArea b)
{
	newArea=b;
}

public JTextArea getDuplicateArea()
{
	return duplicateArea;
}

public JTextArea getNewArea()
{
	return newArea;
}

public double getResetTotal ()
{
	return resetTotal;
}





public JButton getYes ()
{
	return yes;
}
public static void main(String[] args)
{
TransactionVoid b = new TransactionVoid();
b.setVisible(true);

}

}
