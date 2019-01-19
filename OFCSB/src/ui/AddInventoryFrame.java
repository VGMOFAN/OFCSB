package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.11.14
 */

import core.Supply;
import core.Other;
import dao.ProductDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddInventoryFrame extends JFrame implements ActionListener
{
	private InventoryPanel inventoryPanel;
	private ProductDAO dao;
	private Other other;
	private Supply supply;
	private JFrame addInventoryFrame;
	private JPanel addInventoryPanel;
	private JLabel nameLabel, actualNameLabel, scaleLabel, colonLabel, amountLabel, exDateLabel, slash1Label, slash2Label, largeUnitLabel;
	private JTextField smallUnitTF, amountTF, yearTF, monthTF, dayTF;
	private JButton add12Btn, add16Btn, add24Btn, add25Btn, addBtn, cancelBtn;
	
	//inherit the InventoryPanel, productDAO, and one of products. The other one will be null
	public AddInventoryFrame(InventoryPanel thePanel, ProductDAO theDAO, Supply theSupply, Other theOther)
	{
		inventoryPanel = thePanel;
		dao = theDAO;
		supply = theSupply;
		other = theOther;
		
		//the image that is needed for the buttons
		ImageIcon buttonImage = new ImageIcon("Images/Button.png");
				
		//the font for buttons
		Font buttonFont = new Font("Arial", Font.PLAIN, 30);
		
		//settings for addInventoryFrame
		addInventoryFrame = new JFrame("Add Inventory");
		addInventoryFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addInventoryFrame.setPreferredSize(new Dimension(1500,600));
		addInventoryFrame.setLayout(new BorderLayout());
		addInventoryFrame.setResizable(false);
		
		//settings for addInventoryPanel
		addInventoryPanel = new JPanel();
		addInventoryPanel.setLayout(null);//set layout to null because I need to set bounds for JComponents
		
		//a JLabel to indicate the content after it is the name the product
		nameLabel = new JLabel("Product Name:");
		nameLabel.setBounds(20, 20, 200, 50);
		nameLabel.setFont(buttonFont);
		addInventoryPanel.add(nameLabel);
		
		//a JLabel contains the name of the product which will be added new inventory
		actualNameLabel = new JLabel();
		actualNameLabel.setBounds(250, 20, 200, 50);
		actualNameLabel.setFont(buttonFont);
		//if one of other or supply is null, that means the other is the one that will be added new inventory
		if(other == null)
		{
			actualNameLabel.setText(supply.getName());
		}
		else
		{
			actualNameLabel.setText(other.getName());
		}
		addInventoryPanel.add(actualNameLabel);
		
		//a JLabel indicates that the contents after it is the scale of large unit and small unit
		//large unit means something like boxes and small unit means a single unit like a bar of chocolate
		//1:1 means there is no large unit exist
		scaleLabel = new JLabel("Large Unit : Small Unit");
		scaleLabel.setBounds(20, 100, 300, 50);
		scaleLabel.setFont(buttonFont);
		addInventoryPanel.add(scaleLabel);
		
		//a JLabel that contains a colon
		colonLabel = new JLabel(":");
		colonLabel.setBounds(460, 100, 10, 50);
		colonLabel.setFont(buttonFont);
		addInventoryPanel.add(colonLabel);
		
		//a JLabel that indicates the contents after it is the amount of large unit
		amountLabel = new JLabel("Amount");
		amountLabel.setBounds(20, 200, 200, 50);
		amountLabel.setFont(buttonFont);
		addInventoryPanel.add(amountLabel);
		
		//a JLabel that indicates the contents after it is the expiration date of this inventory
		exDateLabel = new JLabel("Expiration Date");
		exDateLabel.setBounds(20, 300, 250, 50);
		exDateLabel.setFont(buttonFont);
		addInventoryPanel.add(exDateLabel);
		
		//a JLabel that contains a slash
		slash1Label = new JLabel("/");
		slash1Label.setBounds(430, 300, 10, 50);
		slash1Label.setFont(buttonFont);
		addInventoryPanel.add(slash1Label);
		
		//a JLabel that contains an other slash
		slash2Label = new JLabel("/");
		slash2Label.setBounds(510, 300, 10, 50);
		slash2Label.setFont(buttonFont);
		addInventoryPanel.add(slash2Label);
		
		//a JLabel that contains the number of large unit, the number is always 1
		largeUnitLabel = new JLabel("1");
		largeUnitLabel.setBounds(400, 100, 60, 50);
		largeUnitLabel.setFont(buttonFont);
		addInventoryPanel.add(largeUnitLabel);
		
		//a JTextField that contains the number of small units, the default number is 1
		smallUnitTF = new JTextField("1");
		smallUnitTF.setBounds(480, 100, 60, 50);
		smallUnitTF.setFont(buttonFont);
		addInventoryPanel.add(smallUnitTF);
		
		//a JTextField that contains the number of amount of inventory, the default number is 0
		amountTF = new JTextField("0");
		amountTF.setBounds(200, 200, 60, 50);
		amountTF.setFont(buttonFont);
		addInventoryPanel.add(amountTF);
		
		//a JTextField that contains the year number of expiration date
		yearTF = new JTextField();
		yearTF.setBounds(300, 300, 120, 50);
		yearTF.setFont(buttonFont);
		yearTF.setHorizontalAlignment(SwingConstants.RIGHT);
		addInventoryPanel.add(yearTF);
		
		//a JTextField that contains the month number of expiration date
		monthTF = new JTextField();
		monthTF.setBounds(440, 300, 60, 50);
		monthTF.setFont(buttonFont);
		monthTF.setHorizontalAlignment(SwingConstants.RIGHT);
		addInventoryPanel.add(monthTF);
		
		//a JTextField that contains the day number of expiration date
		dayTF = new JTextField();
		dayTF.setBounds(520, 300, 60, 50);
		dayTF.setFont(buttonFont);
		dayTF.setHorizontalAlignment(SwingConstants.RIGHT);
		addInventoryPanel.add(dayTF);
		
		//if the type of the product is other, then it does not have a expiration date
		if(supply == null)
		{
			//disable the JTextField so users cannot enter numbers
			setDisabled(yearTF);
			setDisabled(monthTF);
			setDisabled(dayTF);
		}
		
		add12Btn = new JButton(buttonImage);
		add12Btn.setBounds(280, 180, 280, 82);
		add12Btn.setText("+12");
		add12Btn.setFont(buttonFont);
		add12Btn.setHorizontalTextPosition(JButton.CENTER);
		add12Btn.setVerticalTextPosition(JButton.CENTER);
		add12Btn.setContentAreaFilled(false);
		add12Btn.setBorderPainted(false);
		add12Btn.addActionListener(this);
		addInventoryPanel.add(add12Btn);
		
		add16Btn = new JButton(buttonImage);
		add16Btn.setBounds(580, 180, 280, 82);
		add16Btn.setText("+16");
		add16Btn.setFont(buttonFont);
		add16Btn.setHorizontalTextPosition(JButton.CENTER);
		add16Btn.setVerticalTextPosition(JButton.CENTER);
		add16Btn.setContentAreaFilled(false);
		add16Btn.setBorderPainted(false);
		add16Btn.addActionListener(this);
		addInventoryPanel.add(add16Btn);
		
		add24Btn = new JButton(buttonImage);
		add24Btn.setBounds(880, 180, 280, 82);
		add24Btn.setText("+24");
		add24Btn.setFont(buttonFont);
		add24Btn.setHorizontalTextPosition(JButton.CENTER);
		add24Btn.setVerticalTextPosition(JButton.CENTER);
		add24Btn.setContentAreaFilled(false);
		add24Btn.setBorderPainted(false);
		add24Btn.addActionListener(this);
		addInventoryPanel.add(add24Btn);
		
		add25Btn = new JButton(buttonImage);
		add25Btn.setBounds(1180, 180, 280, 82);
		add25Btn.setText("+25");
		add25Btn.setFont(buttonFont);
		add25Btn.setHorizontalTextPosition(JButton.CENTER);
		add25Btn.setVerticalTextPosition(JButton.CENTER);
		add25Btn.setContentAreaFilled(false);
		add25Btn.setBorderPainted(false);
		add25Btn.addActionListener(this);
		addInventoryPanel.add(add25Btn);
		
		addBtn = new JButton(buttonImage);
		addBtn.setBounds(400, 400, 280, 82);
		addBtn.setText("Add");
		addBtn.setFont(buttonFont);
		addBtn.setHorizontalTextPosition(JButton.CENTER);
		addBtn.setVerticalTextPosition(JButton.CENTER);
		addBtn.setContentAreaFilled(false);
		addBtn.setBorderPainted(false);
		addBtn.addActionListener(this);
		addInventoryPanel.add(addBtn);
		
		cancelBtn = new JButton(buttonImage);
		cancelBtn.setBounds(820, 400, 280, 82);
		cancelBtn.setText("Cancel");
		cancelBtn.setFont(buttonFont);
		cancelBtn.setHorizontalTextPosition(JButton.CENTER);
		cancelBtn.setVerticalTextPosition(JButton.CENTER);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setBorderPainted(false);
		cancelBtn.addActionListener(this);
		addInventoryPanel.add(cancelBtn);
		
		addInventoryFrame.add(addInventoryPanel);
		addInventoryFrame.pack();
		addInventoryFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == add12Btn)
		{
			int temp = Integer.valueOf(amountTF.getText());
			temp += 12;
			amountTF.setText(String.valueOf(temp));
		}
		
		if(e.getSource() == add16Btn)
		{
			int temp = Integer.valueOf(amountTF.getText());
			temp += 16;
			amountTF.setText(String.valueOf(temp));
		}
		
		if(e.getSource() == add24Btn)
		{
			int temp = Integer.valueOf(amountTF.getText());
			temp += 24;
			amountTF.setText(String.valueOf(temp));
		}
		
		if(e.getSource() == add25Btn)
		{
			int temp = Integer.valueOf(amountTF.getText());
			temp += 25;
			amountTF.setText(String.valueOf(temp));
		}
		
		if(e.getSource() == addBtn)
		{
			int amount = 0;
			String exDate = "";
			if(other == null)
			{
				amount = Integer.parseInt(smallUnitTF.getText()) * Integer.parseInt(amountTF.getText());
				exDate = yearTF.getText() + " " + monthTF.getText() + " " + dayTF.getText() + "&";
				supply.addAmount(amount);
				supply.addExDate(exDate);
				
				try
				{
					dao.updateSupply(supply);
					addInventoryFrame.dispose();
					inventoryPanel.refreshProductView();
				}
				catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(this, "Error adding inventory: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				amount = Integer.parseInt(smallUnitTF.getText()) * Integer.parseInt(amountTF.getText()) + other.getAmount();
				other.addAmount(amount);
				
				try
				{
					dao.updateOther(other);
					addInventoryFrame.dispose();
					inventoryPanel.refreshProductView();
				}
				catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(this, "Error adding inventory: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		//close this frame after clicking cancel button
		if(e.getSource() == cancelBtn)
		{
			addInventoryFrame.dispose();
		}
	}
	
	//this method can disable selected JComponents and grey them out
	private void setDisabled(JComponent jc)
	{
		jc.setEnabled(false);
		jc.setBackground(Color.LIGHT_GRAY);
	}
}