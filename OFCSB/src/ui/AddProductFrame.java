package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.11.13
 */

import core.Supply;
import core.Menu;
import core.Other;
import dao.ProductDAO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddProductFrame extends JFrame implements ActionListener
{
	private ProductDAO dao;
	private InventoryPanel inventoryPanel;
	private boolean editMode = false;
	private Supply supply;
	private Menu menu;
	private Other other;
	private String type = "";
	private int count = 0;
	@SuppressWarnings("rawtypes")
	private JComboBox[] materialCB = new JComboBox[9];
	private JFrame addProductFrame;
	private JPanel addProductPanel, comboPanel;
	private JButton addCBBtn, addBtn, cancelBtn;
	private JLabel nameLabel, typeLabel, categoryLabel, priceLabel, emPriceLabel, lowStockLabel, materialLabel, amountLabel;
	private JTextField nameTF, priceTF, emPriceTF, lowStockTF, amountTF;
	private JComboBox<String> categoryCB, typeCB;
	
	public AddProductFrame(InventoryPanel thePanel, ProductDAO theDAO, boolean theEditMode, Supply theSupply, Menu theMenu, Other theOther)
	{
		inventoryPanel = thePanel;
		dao = theDAO;
		editMode = theEditMode;
		supply = theSupply;
		menu = theMenu;
		other = theOther;
		
		//the image that is needed for the buttons
		ImageIcon buttonImage = new ImageIcon("Images/Button.png");
				
		//the font for buttons
		Font buttonFont = new Font("Arial", Font.PLAIN, 30);
		
		int supplyNum = 0;
		try
		{
			supplyNum = dao.getLastID("Supply");
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		String[] supplyList = new String[supplyNum];
		try
		{
			supplyList = dao.getSupplyNames();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		addProductFrame = new JFrame("Add Product");
		addProductFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addProductFrame.setPreferredSize(new Dimension(1000, 900));
		addProductFrame.setLayout(new BorderLayout());
		addProductFrame.setResizable(false);
		
		addProductPanel = new JPanel();
		addProductPanel.setLayout(null);
		
		comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(3, 3, 5, 5));
		comboPanel.setBounds(350, 500, 600, 200);
		for(int i = 0; i < materialCB.length; i++)
		{
			materialCB[i] = new JComboBox<String>(supplyList);
			materialCB[i].setFont(buttonFont);
			materialCB[i].setVisible(false);
			comboPanel.add(materialCB[i]);
		}
		addProductPanel.add(comboPanel);
		
		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(20, 20, 300, 50);
		nameLabel.setFont(buttonFont);
		addProductPanel.add(nameLabel);
		
		typeLabel = new JLabel("Type:");
		typeLabel.setBounds(20, 100, 300, 50);
		typeLabel.setFont(buttonFont);
		addProductPanel.add(typeLabel);
		
		categoryLabel = new JLabel("Category:");
		categoryLabel.setBounds(20, 180, 300, 50);
		categoryLabel.setFont(buttonFont);
		addProductPanel.add(categoryLabel);
		
		priceLabel = new JLabel("Price:");
		priceLabel.setBounds(20, 260, 300, 50);
		priceLabel.setFont(buttonFont);
		addProductPanel.add(priceLabel);
		
		emPriceLabel = new JLabel("Employee Price:");
		emPriceLabel.setBounds(20, 340, 300, 50);
		emPriceLabel.setFont(buttonFont);
		addProductPanel.add(emPriceLabel);
		
		lowStockLabel = new JLabel("Low-Stock:");
		lowStockLabel.setBounds(20, 420, 300, 50);
		lowStockLabel.setFont(buttonFont);
		addProductPanel.add(lowStockLabel);
		
		materialLabel = new JLabel("Materials:");
		materialLabel.setBounds(20, 500, 300, 50);
		materialLabel.setFont(buttonFont);
		addProductPanel.add(materialLabel);
		
		nameTF = new JTextField();
		nameTF.setBounds(350, 20, 300, 50);
		nameTF.setFont(buttonFont);
		addProductPanel.add(nameTF);
		
		priceTF = new JTextField("0");
		priceTF.setBounds(350, 260, 300, 50);
		priceTF.setFont(buttonFont);
		addProductPanel.add(priceTF);
		
		emPriceTF = new JTextField("0");
		emPriceTF.setBounds(350, 340, 300, 50);
		emPriceTF.setFont(buttonFont);
		addProductPanel.add(emPriceTF);
		
		lowStockTF = new JTextField("0");
		lowStockTF.setBounds(350, 420, 300, 50);
		lowStockTF.setFont(buttonFont);
		addProductPanel.add(lowStockTF);

		String[] categoryList = {"", "Candy", "Popcorn", "Small drink", "Large drink", "Hotdog"};
		categoryCB = new JComboBox<String>(categoryList);
		categoryCB.setSelectedIndex(0);
		categoryCB.setBounds(350, 180, 300, 50);
		categoryCB.setFont(buttonFont);
		addProductPanel.add(categoryCB);
		
		String[] typeList = {"", "Supply", "Menu", "Other"};
		typeCB = new JComboBox<String>(typeList);
		typeCB.setSelectedIndex(0);
		typeCB.setBounds(350, 100, 300, 50);
		typeCB.setFont(buttonFont);
		typeCB.addActionListener(new ActionListener()
				{
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent e)
					{
						JComboBox<String> combo = (JComboBox<String>) e.getSource();
				        type = (String) combo.getSelectedItem();
				        
				        switch(type)
				        {
				        case "":
				        	break;
				        case "Supply":
				        	type = "Supply";
				        	setDisabled(priceTF);
				        	setDisabled(emPriceTF);
				        	setDisabled(addCBBtn);
				        	setDisabled(comboPanel);
				        	setDisabled(categoryCB);
				        	for(int i = 0; i < materialCB.length; i++)
				    		{
				    			setDisabled(materialCB[i]);
				    		}
				        	setEnabled(lowStockTF);
				        	break;
				        case "Menu":
				        	type = "Menu";
				        	setEnabled(priceTF);
				        	setEnabled(emPriceTF);
				        	setEnabled(addCBBtn);
				        	setEnabled(comboPanel);
				        	setEnabled(categoryCB);
				        	for(int i = 0; i < materialCB.length; i++)
				    		{
				    			setEnabled(materialCB[i]);
				    		}
				        	setDisabled(lowStockTF);
				        	break;
				        case "Other":
				        	type = "Other";
				        	setDisabled(priceTF);
				        	setDisabled(emPriceTF);
				        	setDisabled(addCBBtn);
				        	setDisabled(comboPanel);
				        	setDisabled(categoryCB);
				        	for(int i = 0; i < materialCB.length; i++)
				    		{
				    			setEnabled(materialCB[i]);
				    		}
				        	setEnabled(lowStockTF);
				        	break;
				        }
					}
				});
		addProductPanel.add(typeCB);
		
		addCBBtn = new JButton("+");
		addCBBtn.setBounds(200, 500, 60, 60);
		addCBBtn.setFont(new Font("Arial", Font.BOLD, 30));
		addCBBtn.addActionListener(this);
		addProductPanel.add(addCBBtn);
		
		addBtn = new JButton(buttonImage);
		addBtn.setBounds(100, 750, 280, 82);
		addBtn.setText("Add");
		addBtn.setFont(buttonFont);
		addBtn.setHorizontalTextPosition(JButton.CENTER);
		addBtn.setVerticalTextPosition(JButton.CENTER);
		addBtn.setContentAreaFilled(false);
		addBtn.setBorderPainted(false);
		addBtn.addActionListener(this);
		addProductPanel.add(addBtn);
		
		cancelBtn = new JButton(buttonImage);
		cancelBtn.setBounds(400, 750, 280, 82);
		cancelBtn.setText("Cancel");
		cancelBtn.setFont(buttonFont);
		cancelBtn.setHorizontalTextPosition(JButton.CENTER);
		cancelBtn.setVerticalTextPosition(JButton.CENTER);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setBorderPainted(false);
		cancelBtn.addActionListener(this);
		addProductPanel.add(cancelBtn);
		
		if(editMode == true)
		{
			addProductFrame.setTitle("Edit Product");
			
			setDisabled(typeCB);
			
			amountLabel = new JLabel("Amount:");
			amountLabel.setBounds(20, 370, 100, 33);
			addProductFrame.add(amountLabel);
			
			amountTF = new JTextField();
			amountTF.setBounds(150, 370, 150, 33);
			addProductFrame.add(amountTF);
			
			addBtn.setText("Apply changes");
			
			if(supply != null)
			{
				nameTF.setText(supply.getName());
				type = "Supply";
				typeCB.setSelectedItem("Supply");
				lowStockTF.setText("" + supply.getLowStock());
				amountTF.setText("" + supply.getAmount());
			}
			else if(menu != null)
			{
				nameTF.setText(menu.getName());
				type = "Menu";
				typeCB.setSelectedItem("Menu");
				priceTF.setText("" + menu.getPrice());
				emPriceTF.setText("" + menu.getEmployeePrice());
				categoryCB.setSelectedItem(menu.getCategory());
				
				String[] materials = theMenu.getMaterial().split("&");
				for(int i = 0; i < materials.length; i++)
				{
					if(materials[i].compareTo("") != 0)
					{
						materialCB[i].setVisible(true);
						materialCB[i].setSelectedItem(materials[i]);
						count++;
					}
				}
				
				setDisabled(amountTF);
			}
			else
			{
				nameTF.setText(other.getName());
				type = "Other";
				typeCB.setSelectedItem("Other");
				lowStockTF.setText("" + other.getLowStock());
				amountTF.setText("" + other.getAmount());
			}
		}
		else
		{
			amountLabel = null;
			amountTF = null;
		}
		
		addProductFrame.add(addProductPanel);
		addProductFrame.pack();
		addProductFrame.setVisible(true);
	}
	
	public AddProductFrame(InventoryPanel thePanel, ProductDAO theDAO)
	{
		this(thePanel, theDAO, false, null, null, null);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addCBBtn)
		{
			materialCB[count].setVisible(true);
			count++;
			if(count == 9)
			{
				addCBBtn.setVisible(false);
			}
		}
		
		if(e.getSource() == addBtn)
		{
			if(type.compareTo("Supply") == 0 || type.compareTo("Other") == 0)
			{
				String name = nameTF.getText();
				int lowStock = Integer.parseInt(lowStockTF.getText());
				
				if(type.compareTo("Supply") == 0)
				{
					if(editMode)
					{
						supply.setName(name);
						supply.setLowStock(lowStock);
						supply.setAmount(Integer.parseInt(amountTF.getText()));
						try
						{
							dao.updateSupply(supply);
							inventoryPanel.refreshProductView();
							addProductFrame.dispose();
						}
						catch (Exception e1)
						{
							JOptionPane.showMessageDialog(this, "Error applying the changes: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						Supply temp = new Supply(name, type, lowStock);
						try
						{
							dao.addSupply(temp);
							addProductFrame.dispose();
							inventoryPanel.refreshProductView();
						}
						catch (Exception e1)
						{
							JOptionPane.showMessageDialog(this, "Error adding product: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else
				{
					if(editMode)
					{
						other.setName(name);
						other.setLowStock(lowStock);
						other.setAmount(Integer.parseInt(amountTF.getText()));
						try
						{
							dao.updateOther(other);
							inventoryPanel.refreshProductView();
							addProductFrame.dispose();
						}
						catch (Exception e1)
						{
							JOptionPane.showMessageDialog(this, "Error applying the changes: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
					{
						Other temp = new Other(name, type, lowStock);
						try
						{
							dao.addOther(temp);
							inventoryPanel.refreshProductView();
							addProductFrame.dispose();
						}
						catch (Exception e1)
						{
							JOptionPane.showMessageDialog(this, "Error adding product: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			else if(type.compareTo("Menu") == 0)
			{
				String name = nameTF.getText();
				String category = (String) categoryCB.getSelectedItem();
				String material = "";
				for(int i = 0; i < count; i++)
				{
					String temp = (String) materialCB[i].getSelectedItem();
					if(temp.compareTo("") != 0)
					{
						material = material + temp + "&";
					}
				}
				double price = Double.parseDouble(priceTF.getText());
				double emPrice = Double.parseDouble(emPriceTF.getText());
				
				if(editMode)
				{
					menu.setName(name);
					menu.setCategory(category);
					menu.setMaterial(material);
					menu.setPrice(price);
					menu.setEmployeePrice(emPrice);
					
					try
					{
						dao.updateMenu(menu);
						inventoryPanel.refreshProductView();
						addProductFrame.dispose();
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog(this, "Error applying the changes: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					Menu temp = new Menu(name, type, category, material, price, emPrice);
					try
					{
						dao.addMenu(temp);
						inventoryPanel.refreshProductView();
						addProductFrame.dispose();
					}
					catch (Exception e1)
					{
						JOptionPane.showMessageDialog(this, "Error adding product: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Please choose the type for the product.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == cancelBtn)
		{
			addProductFrame.dispose();
		}
	}
	
	private void setDisabled(JComponent jc)
	{
		jc.setEnabled(false);
		jc.setBackground(Color.LIGHT_GRAY);
	}
	
	private void setEnabled(JComponent jc)
	{
		jc.setEnabled(true);
		jc.setBackground(Color.WHITE);
	}
}