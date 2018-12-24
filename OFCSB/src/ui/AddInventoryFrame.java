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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
	
	public AddInventoryFrame(InventoryPanel thePanel, ProductDAO theDAO, Supply theSupply, Other theOther)
	{
		inventoryPanel = thePanel;
		dao = theDAO;
		supply = theSupply;
		other = theOther;
		
		addInventoryFrame = new JFrame("Add Inventory");
		addInventoryFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addInventoryFrame.setPreferredSize(new Dimension(650,500));
		addInventoryFrame.setLayout(new BorderLayout());
		addInventoryFrame.setResizable(false);
		
		addInventoryPanel = new JPanel();
		addInventoryPanel.setLayout(null);
		
		nameLabel = new JLabel("Product Name:");
		nameLabel.setBounds(20, 20, 100, 33);
		addInventoryPanel.add(nameLabel);
		
		actualNameLabel = new JLabel();
		actualNameLabel.setBounds(200, 20, 100, 33);
		if(other == null)
		{
			actualNameLabel.setText(supply.getName());
		}
		else
		{
			actualNameLabel.setText(other.getName());
		}
		addInventoryPanel.add(actualNameLabel);
		
		scaleLabel = new JLabel("Large Unit : Small Unit");
		scaleLabel.setBounds(20, 70, 140, 33);
		addInventoryPanel.add(scaleLabel);
		
		colonLabel = new JLabel(":");
		colonLabel.setBounds(265, 70, 5, 33);
		addInventoryPanel.add(colonLabel);
		
		amountLabel = new JLabel("Amount");
		amountLabel.setBounds(20, 120, 100, 33);
		addInventoryPanel.add(amountLabel);
		
		exDateLabel = new JLabel("Expiration Date");
		exDateLabel.setBounds(20, 170, 100, 33);
		addInventoryPanel.add(exDateLabel);
		
		slash1Label = new JLabel("/");
		slash1Label.setBounds(282, 170, 10, 33);
		addInventoryPanel.add(slash1Label);
		
		slash2Label = new JLabel("/");
		slash2Label.setBounds(333, 170, 10, 33);
		addInventoryPanel.add(slash2Label);
		
		largeUnitLabel = new JLabel("1");
		largeUnitLabel.setBounds(200, 70, 60, 33);
		addInventoryPanel.add(largeUnitLabel);
		
		smallUnitTF = new JTextField("1");
		smallUnitTF.setBounds(271, 70, 60, 33);
		addInventoryPanel.add(smallUnitTF);
		
		amountTF = new JTextField("0");
		amountTF.setBounds(200, 120, 60, 33);
		addInventoryPanel.add(amountTF);
		
		yearTF = new JTextField();
		yearTF.setBounds(200, 170, 80, 33);
		yearTF.setHorizontalAlignment(SwingConstants.RIGHT);
		addInventoryPanel.add(yearTF);
		
		monthTF = new JTextField();
		monthTF.setBounds(290, 170, 40, 33);
		monthTF.setHorizontalAlignment(SwingConstants.RIGHT);
		addInventoryPanel.add(monthTF);
		
		dayTF = new JTextField();
		dayTF.setBounds(340, 170, 40, 33);
		dayTF.setHorizontalAlignment(SwingConstants.RIGHT);
		addInventoryPanel.add(dayTF);
		
		if(supply == null)
		{
			setDisabled(yearTF);
			setDisabled(monthTF);
			setDisabled(dayTF);
		}
		
		add12Btn = new JButton("+12");
		add12Btn.setBounds(280, 120, 60, 33);
		add12Btn.addActionListener(this);
		addInventoryPanel.add(add12Btn);
		
		add16Btn = new JButton("+16");
		add16Btn.setBounds(360, 120, 60, 33);
		add16Btn.addActionListener(this);
		addInventoryPanel.add(add16Btn);
		
		add24Btn = new JButton("+24");
		add24Btn.setBounds(440, 120, 60, 33);
		add24Btn.addActionListener(this);
		addInventoryPanel.add(add24Btn);
		
		add25Btn = new JButton("+25");
		add25Btn.setBounds(520, 120, 60, 33);
		add25Btn.addActionListener(this);
		addInventoryPanel.add(add25Btn);
		
		addBtn = new JButton("Add");
		addBtn.setBounds(100, 400, 120, 33);
		addBtn.addActionListener(this);
		addInventoryPanel.add(addBtn);
		
		cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(400, 400, 120, 33);
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
		
		if(e.getSource() == cancelBtn)
		{
			addInventoryFrame.dispose();
		}
	}
	
	private void setDisabled(JComponent jc)
	{
		jc.setEnabled(false);
		jc.setBackground(Color.LIGHT_GRAY);
	}
}