package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.10.26
 */

import core.Supply;
import core.Menu;
import core.Other;
import dao.ProductDAO;
import tms.SupplyTableModel;
import tms.MenuTableModel;
import tms.OtherTableModel;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class InventoryPanel implements ActionListener
{
	private ProductDAO dao;
	private DefaultTableCellRenderer dtcr;
	private JScrollPane supplyPane, menuPane, otherPane;
	private JTable supplyTable, menuTable, otherTable;
	private JTextField searchTF;
	private JPanel inventoryPanel, switchPanel;
	private JButton homeBtn, searchBtn, expirationBtn, lowStockBtn, addInventoryBtn, editBtn, addProductBtn, deleteBtn, supplyBtn, menuBtn, otherBtn, recordBtn;
	private String card = "Supply";
	
	//constructor
	public InventoryPanel()
	{	
		try
		{
			dao = new ProductDAO();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error connecting to database: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		//setting for inventory panel
		inventoryPanel = new JPanel();
		inventoryPanel.setLayout(null);
		
		switchPanel = new JPanel();
		switchPanel.setLayout(new CardLayout());
		switchPanel.setBounds(10, 70, 500, 350);
		inventoryPanel.add(switchPanel);
		
		dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		
		supplyTable = new JTable();
		try
		{
			List<Supply> supplyList = dao.getAllSupplies();
			SupplyTableModel supplyModel = new SupplyTableModel(supplyList);
			supplyTable.setModel(supplyModel);
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < supplyTable.getColumnCount(); i++)
		{
			supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		menuTable = new JTable();
		try
		{
			List<Menu> menuList = dao.getAllMenus();
			MenuTableModel menuModel = new MenuTableModel(menuList);
			menuTable.setModel(menuModel);
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < menuTable.getColumnCount(); i++)
		{
			menuTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		otherTable = new JTable();
		try
		{
			List<Other> otherList = dao.getAllOthers();
			OtherTableModel otherModel = new OtherTableModel(otherList);
			otherTable.setModel(otherModel);
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < otherTable.getColumnCount(); i++)
		{
			otherTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		//a scroll pane with a list of supplies
		supplyPane = new JScrollPane();
		supplyPane.setViewportView(supplyTable);
		switchPanel.add(supplyPane, "Supply");
		
		menuPane = new JScrollPane();
		menuPane.setViewportView(menuTable);
		switchPanel.add(menuPane, "Menu");
		
		otherPane = new JScrollPane();
		otherPane.setViewportView(otherTable);
		switchPanel.add(otherPane, "Other");
		
		//search bar to search products
		searchTF = new JTextField();
		searchTF.setBounds(10, 20, 300, 33);
		inventoryPanel.add(searchTF);
		
		supplyBtn = new JButton("Supply");
		supplyBtn.setBounds(100, 430, 100, 33);
		supplyBtn.addActionListener(this);
		inventoryPanel.add(supplyBtn);
		
		menuBtn = new JButton("Menu");
		menuBtn.setBounds(200, 430, 100, 33);
		menuBtn.addActionListener(this);
		inventoryPanel.add(menuBtn);
		
		otherBtn = new JButton("Other");
		otherBtn.setBounds(300, 430, 100, 33);
		otherBtn.addActionListener(this);
		inventoryPanel.add(otherBtn);
		
		homeBtn = new JButton("Home");
		homeBtn.setBounds(550, 20, 50, 50);
		homeBtn.addActionListener(this);
		inventoryPanel.add(homeBtn);
		
		//search button to search
		searchBtn = new JButton("Search");
		searchBtn.setBounds(320, 20, 100, 33);
		searchBtn.addActionListener(this);
		inventoryPanel.add(searchBtn);
		
		expirationBtn = new JButton("Expirations");
		expirationBtn.setBounds(520, 90, 120, 33);
		expirationBtn.addActionListener(this);
		inventoryPanel.add(expirationBtn);
		
		lowStockBtn = new JButton("Low-stocks");
		lowStockBtn.setBounds(520, 140, 120, 33);
		lowStockBtn.addActionListener(this);
		inventoryPanel.add(lowStockBtn);
		
		//create a new frame to add inventory to existing products if this button is clicked
		addInventoryBtn = new JButton("Add Inventory");
		addInventoryBtn.setBounds(520, 190, 120, 33);
		addInventoryBtn.addActionListener(this);
		inventoryPanel.add(addInventoryBtn);
		
		//create a new frame to edit the properties of selected product if this button is clicked
		editBtn = new JButton("Edit Product");
		editBtn.setBounds(520, 240, 120, 33);
		editBtn.addActionListener(this);
		inventoryPanel.add(editBtn);
		
		//create a new frame to add a new type of product if this button is clicked
		addProductBtn = new JButton("Add Product");
		addProductBtn.setBounds(520, 290, 120, 33);
		addProductBtn.addActionListener(this);
		inventoryPanel.add(addProductBtn);
		
		//delete selected product from the list
		deleteBtn = new JButton("Delete Product");
		deleteBtn.setBounds(520, 340, 120, 33);
		deleteBtn.addActionListener(this);
		inventoryPanel.add(deleteBtn);
		
		recordBtn = new JButton("Records");
		recordBtn.setBounds(520, 390, 120, 33);
		recordBtn.addActionListener(this);
		inventoryPanel.add(recordBtn);
		
		//add the inventory panel to the main frame and pack
		MainFrame.overallFrame.add(inventoryPanel);
		MainFrame.overallFrame.pack();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == supplyBtn)
		{
			CardLayout cardLayout = (CardLayout) switchPanel.getLayout();
			cardLayout.show(switchPanel, "Supply");
			card = "Supply";
			menuTable.getSelectionModel().clearSelection();
			otherTable.getSelectionModel().clearSelection();
		}
		
		if(e.getSource() == menuBtn)
		{
			CardLayout cardLayout = (CardLayout) switchPanel.getLayout();
			cardLayout.show(switchPanel, "Menu");
			card = "Menu";
			supplyTable.getSelectionModel().clearSelection();
			otherTable.getSelectionModel().clearSelection();
		}
		
		if(e.getSource() == otherBtn)
		{
			CardLayout cardLayout = (CardLayout) switchPanel.getLayout();
			cardLayout.show(switchPanel, "Other");
			card = "Other";
			supplyTable.getSelectionModel().clearSelection();
			menuTable.getSelectionModel().clearSelection();
		}
		
		if(e.getSource() == homeBtn)
		{
			MainFrame.overallFrame.getContentPane().removeAll();
			MainFrame.overallFrame.repaint();
			new MainPanel();
		}

		if(e.getSource() == expirationBtn)
		{
			new ExpirationFrame(dao);
		}
		
		if(e.getSource() == lowStockBtn)
		{
			new LowStockFrame(dao);
		}
		
		if(e.getSource() == addInventoryBtn)
		{
			try
			{
				int row = -1;
				if(card.compareTo("Supply") == 0)
				{
					row = supplyTable.getSelectedRow();
				}
				else if(card.compareTo("Other") == 0)
				{
					row = otherTable.getSelectedRow();
				}
				else
				{
					JOptionPane.showMessageDialog(MainFrame.overallFrame, "You must select a product from Supply or Other", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(row < 0)
				{
					JOptionPane.showMessageDialog(MainFrame.overallFrame, "You must select a product", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(card.compareTo("Supply") == 0)
				{
					Supply temp = dao.getAllSupplies().get(row);
					new AddInventoryFrame(this, dao, temp, null);
				}
				else
				{
					Other temp = dao.getAllOthers().get(row);
					new AddInventoryFrame(this, dao, null, temp);
				}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error adding inventory: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == addProductBtn)
		{
			//create a new frame to add new products
			new AddProductFrame(this, dao);
		}
		
		if(e.getSource() == editBtn)
		{
			try
			{
				int row = -1;
				if(card.compareTo("Supply") == 0)
				{
					row = supplyTable.getSelectedRow();
				}
				else if(card.compareTo("Menu") == 0)
				{
					row = menuTable.getSelectedRow();
				}
				else
				{
					row = otherTable.getSelectedRow();
				}
				
				if(row < 0)
				{
					JOptionPane.showMessageDialog(MainFrame.overallFrame, "You must select a product", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(card.compareTo("Supply") == 0)
				{
					new AddProductFrame(this, dao, true, dao.getAllSupplies().get(row), null, null);
				}
				else if(card.compareTo("Menu") == 0)
				{
					new AddProductFrame(this, dao, true, null, dao.getAllMenus().get(row), null);
				}
				else
				{
					new AddProductFrame(this, dao, true, null, null, dao.getAllOthers().get(row));
				}
			}
			catch (Exception e1) 
			{
				JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error deleting product: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == searchBtn)
		{
			String name = searchTF.getText();
			if(name.compareTo("") == 0)
			{
				refreshProductView();
			}
			else
			{
				try
				{
					if(card.compareTo("Supply") == 0)
					{
						List<Supply> temp = dao.searchSupply(name);
						SupplyTableModel supplyModel = new SupplyTableModel(temp);
						supplyTable.setModel(supplyModel);
						for(int i = 0; i < supplyTable.getColumnCount(); i++)
						{
							supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
						}
					}
					else if(card.compareTo("Menu") == 0)
					{
						List<Menu> temp = dao.searchMenu(name);
						MenuTableModel menuModel = new MenuTableModel(temp);
						menuTable.setModel(menuModel);
						for(int i = 0; i < menuTable.getColumnCount(); i++)
						{
							menuTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
						}
					}
					else
					{
						List<Other> temp = dao.searchOther(name);
						OtherTableModel otherModel = new OtherTableModel(temp);
						otherTable.setModel(otherModel);
						for(int i = 0; i < otherTable.getColumnCount(); i++)
						{
							otherTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
						}
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error searching product: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if(e.getSource() == deleteBtn)
		{
			try
			{
				int row = -1;
				if(card.compareTo("Supply") == 0)
				{
					row = supplyTable.getSelectedRow();
				}
				else if(card.compareTo("Menu") == 0)
				{
					row = menuTable.getSelectedRow();
				}
				else
				{
					row = otherTable.getSelectedRow();
				}
				
				if(row < 0)
				{
					JOptionPane.showMessageDialog(MainFrame.overallFrame, "You must select a product", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int response = JOptionPane.showConfirmDialog(MainFrame.overallFrame, "Delete this product?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if (response != JOptionPane.YES_OPTION)
				{
					return;
				}
				
				boolean right = (new PasswordDialog()).getIsRight();
				
				if(!right)
				{
					return;
				}
				
				if(card.compareTo("Supply") == 0)
				{
					List<Supply> temp = dao.getAllSupplies();
					dao.deleteProduct("Supply", temp.get(row).getID());
				}
				else if(card.compareTo("Menu") == 0)
				{
					List<Menu> temp = dao.getAllMenus();
					dao.deleteProduct("Menu", temp.get(row).getID());
				}
				else
				{
					List<Other> temp = dao.getAllOthers();
					dao.deleteProduct("Other", temp.get(row).getID());
				}
				refreshProductView();
				JOptionPane.showMessageDialog(MainFrame.overallFrame, "Product deleted succesfully.", "Product Deleted", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception e1) 
			{
				JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error deleting product: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == recordBtn)
		{
			new RecordFrame(dao);
		}
	}
	
	public void refreshProductView()
	{
		try
		{
			List<Supply> supply = dao.getAllSupplies();
			SupplyTableModel supplyModel = new SupplyTableModel(supply);
			supplyTable.setModel(supplyModel);
			for(int i = 0; i < supplyTable.getColumnCount(); i++)
			{
				supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
			
			List<Menu> menu = dao.getAllMenus();
			MenuTableModel menuModel = new MenuTableModel(menu);
			menuTable.setModel(menuModel);
			for(int i = 0; i < menuTable.getColumnCount(); i++)
			{
				menuTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
			
			List<Other> other = dao.getAllOthers();
			OtherTableModel otherModel = new OtherTableModel(other);
			otherTable.setModel(otherModel);
			for(int i = 0; i < otherTable.getColumnCount(); i++)
			{
				otherTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
		}
		catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(MainFrame.overallFrame, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}