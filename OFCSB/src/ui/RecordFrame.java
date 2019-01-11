package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.12.13
 */

import core.Record;
import dao.ProductDAO;
import tms.RecordTableModel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RecordFrame extends JFrame implements ActionListener
{
	private ProductDAO dao;
	private DefaultTableCellRenderer dtcr;
	private JFrame recordFrame;
	private JPanel recordPanel, switchPanel;
	private JScrollPane editPricePane, deleteInventoryPane, debitTransPane;
	private JTable editPriceTable, deleteInventoryTable, debitTransTable;
	private JButton editPriceBtn, deleteInventoryBtn, debitTransBtn, deleteBtn, clearBtn, okBtn;
	private JLabel totalLabel;
	private List<Record> editPriceList, deleteInventoryList, debitTransList;
	private String card = "Edit Price";
	private double total;
	
	public RecordFrame(ProductDAO theDAO)
	{
		dao = theDAO;
		
		//the image that is needed for the buttons
		ImageIcon buttonImage = new ImageIcon("Images/Button.png");
				
		//the font for buttons
		Font buttonFont = new Font("Arial", Font.PLAIN, 30);
		
		Font headerFont = new Font("Arial", Font.BOLD, 30);
		
		dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		
		recordFrame = new JFrame("Records");
		recordFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		recordFrame.setPreferredSize(new Dimension(650,500));
		recordFrame.setLayout(new BorderLayout());
		recordFrame.setResizable(false);
		
		recordPanel = new JPanel();
		recordPanel.setLayout(null);
		
		switchPanel = new JPanel();
		switchPanel.setLayout(new CardLayout());
		switchPanel.setBounds(10, 20, 500, 350);
		recordPanel.add(switchPanel);
		
		editPriceTable = new JTable();
		try
		{
			editPriceList = dao.getAllRecords("Edit Price");
			RecordTableModel recordModel = new RecordTableModel(editPriceList);
			editPriceTable.setModel(recordModel);
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(1));
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(5));
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(5));
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(5));
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < editPriceTable.getColumnCount(); i++)
		{
			editPriceTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		editPriceTable.setFont(buttonFont);
		editPriceTable.getTableHeader().setFont(headerFont);
		editPriceTable.setRowHeight(60);
		
		deleteInventoryTable = new JTable();
		try
		{
			deleteInventoryList = dao.getAllRecords("Delete Inventory");
			RecordTableModel recordModel = new RecordTableModel(deleteInventoryList);
			deleteInventoryTable.setModel(recordModel);
			for(int i = 0; i < 5; i++)
			{
				deleteInventoryTable.removeColumn(deleteInventoryTable.getColumnModel().getColumn(1));
			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < deleteInventoryTable.getColumnCount(); i++)
		{
			deleteInventoryTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		deleteInventoryTable.setFont(buttonFont);
		deleteInventoryTable.getTableHeader().setFont(headerFont);
		deleteInventoryTable.setRowHeight(60);
		
		debitTransTable = new JTable();
		try
		{
			debitTransList = dao.getAllRecords("Debit Transaction");
			RecordTableModel recordModel = new RecordTableModel(debitTransList);
			debitTransTable.setModel(recordModel);
			for(int i = 0; i < 6; i++)
			{
				debitTransTable.removeColumn(debitTransTable.getColumnModel().getColumn(2));
			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < debitTransTable.getColumnCount(); i++)
		{
			debitTransTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		debitTransTable.setFont(buttonFont);
		debitTransTable.getTableHeader().setFont(headerFont);
		debitTransTable.setRowHeight(60);
		
		editPricePane = new JScrollPane();
		editPricePane.setViewportView(editPriceTable);
		switchPanel.add(editPricePane, "Edit Price");
		
		deleteInventoryPane = new JScrollPane();
		deleteInventoryPane.setViewportView(deleteInventoryTable);
		switchPanel.add(deleteInventoryPane, "Delete Inventory");
		
		debitTransPane = new JScrollPane();
		debitTransPane.setViewportView(debitTransTable);
		switchPanel.add(debitTransPane, "Debit Transaction");
		
		editPriceBtn = new JButton(buttonImage);
		editPriceBtn.setBounds(70, 440, 150, 33);
		editPriceBtn.setText("Edited price");
		editPriceBtn.setFont(buttonFont);
		editPriceBtn.setHorizontalTextPosition(JButton.CENTER);
		editPriceBtn.setVerticalTextPosition(JButton.CENTER);
		editPriceBtn.setContentAreaFilled(false);
		editPriceBtn.setBorderPainted(false);
		editPriceBtn.addActionListener(this);
		recordPanel.add(editPriceBtn);
		
		deleteInventoryBtn = new JButton(buttonImage);
		deleteInventoryBtn.setBounds(230, 440, 150, 33);
		deleteInventoryBtn.setText("Deleted inventory");
		deleteInventoryBtn.setFont(buttonFont);
		deleteInventoryBtn.setHorizontalTextPosition(JButton.CENTER);
		deleteInventoryBtn.setVerticalTextPosition(JButton.CENTER);
		deleteInventoryBtn.setContentAreaFilled(false);
		deleteInventoryBtn.setBorderPainted(false);
		deleteInventoryBtn.addActionListener(this);
		recordPanel.add(deleteInventoryBtn);
		
		debitTransBtn = new JButton(buttonImage);
		debitTransBtn.setBounds(400, 440, 150, 33);
		debitTransBtn.setText("Debit Transaction");
		debitTransBtn.setFont(buttonFont);
		debitTransBtn.setHorizontalTextPosition(JButton.CENTER);
		debitTransBtn.setVerticalTextPosition(JButton.CENTER);
		debitTransBtn.setContentAreaFilled(false);
		debitTransBtn.setBorderPainted(false);
		debitTransBtn.addActionListener(this);
		recordPanel.add(debitTransBtn);
		
		deleteBtn = new JButton(buttonImage);
		deleteBtn.setBounds(550, 70, 120 ,33);
		deleteBtn.setText("Delete record");
		deleteBtn.setFont(buttonFont);
		deleteBtn.setHorizontalTextPosition(JButton.CENTER);
		deleteBtn.setVerticalTextPosition(JButton.CENTER);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setBorderPainted(false);
		deleteBtn.addActionListener(this);
		recordPanel.add(deleteBtn);
		
		clearBtn = new JButton(buttonImage);
		clearBtn.setBounds(550, 150, 120 ,33);
		clearBtn.setText("Clear all");
		clearBtn.setFont(buttonFont);
		clearBtn.setHorizontalTextPosition(JButton.CENTER);
		clearBtn.setVerticalTextPosition(JButton.CENTER);
		clearBtn.setContentAreaFilled(false);
		clearBtn.setBorderPainted(false);
		clearBtn.addActionListener(this);
		recordPanel.add(clearBtn);
		
		okBtn = new JButton(buttonImage);
		okBtn.setBounds(550, 250, 120 ,33);
		okBtn.setText("OK");
		okBtn.setFont(buttonFont);
		okBtn.setHorizontalTextPosition(JButton.CENTER);
		okBtn.setVerticalTextPosition(JButton.CENTER);
		okBtn.setContentAreaFilled(false);
		okBtn.setBorderPainted(false);
		okBtn.addActionListener(this);
		recordPanel.add(okBtn);
		
		total = 0;
		for(int i = 0; i < editPriceTable.getRowCount(); i++)
		{
	        double amount = Double.parseDouble(editPriceTable.getValueAt(i, 4) + "");
	        total = amount + total;
		}
		
		totalLabel = new JLabel();
		totalLabel.setText("Total: $" + total);
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBounds(100, 380, 300, 33);
		recordPanel.add(totalLabel);
		
		recordFrame.add(recordPanel);
		recordFrame.pack();
		recordFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == editPriceBtn)
		{
			CardLayout cardLayout = (CardLayout) switchPanel.getLayout();
			cardLayout.show(switchPanel, "Edit Price");
			card = "Edit Price";
			
			total = 0;
			for(int i = 0; i < editPriceTable.getRowCount(); i++)
			{
		        double amount = Double.parseDouble(editPriceTable.getValueAt(i, 4) + "");
		        total = amount + total;
			}
			totalLabel.setText("Total: $" + total);
		}
		
		if(e.getSource() == deleteInventoryBtn)
		{
			CardLayout cardLayout = (CardLayout) switchPanel.getLayout();
			cardLayout.show(switchPanel, "Delete Inventory");
			card = "Delete Inventory";
			
			totalLabel.setText("Total: $0");
		}
		
		if(e.getSource() == debitTransBtn)
		{
			CardLayout cardLayout = (CardLayout) switchPanel.getLayout();
			cardLayout.show(switchPanel, "Debit Transaction");
			card = "Debit Transaction";
			
			total = 0;
			for(int i = 0; i < debitTransTable.getRowCount(); i++)
			{
		        double amount = Double.parseDouble(debitTransTable.getValueAt(i, 3) + "");
		        total = amount + total;
			}
			totalLabel.setText("Total: $" + total);
		}
		
		if(e.getSource() == deleteBtn)
		{
			//row probably needs to minus 1
			int row = -1;
			if(card.compareTo("Edit Price") == 0)
			{
				row = editPriceTable.getSelectedRow();
			}
			else if(card.compareTo("Delete Inventory") == 0)
			{
				row = deleteInventoryTable.getSelectedRow();
			}
			else
			{
				row = debitTransTable.getSelectedRow();
			}
			
			if(row < 0)
			{
				JOptionPane.showMessageDialog(this, "You must select a record: ", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int id = -1;
			if(card.compareTo("Edit Price") == 0)
			{
				id = editPriceList.get(row).getID();
			}
			else if(card.compareTo("Delete Inventory") == 0)
			{
				id = deleteInventoryList.get(row).getID();
			}
			else
			{
				id = debitTransList.get(row).getID();
			}
			
			try
			{
				dao.deleteRecord(id);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "Error deleting the record", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			refreshRecordView();
		}
		
		if(e.getSource() == clearBtn)
		{
			boolean right = (new PasswordDialog()).getIsRight();
			if(!right)
			{
				return;
			}
			
			try
			{
				if(card.compareTo("Edit Price") == 0)
				{
					dao.clearAllRecord(card);
				}
				else if(card.compareTo("Delete Inventory") == 0)
				{
					dao.clearAllRecord(card);
				}
				else
				{
					dao.clearAllRecord(card);
				}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "Error clearing records: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			refreshRecordView();
		}
		
		if(e.getSource() == okBtn)
		{
			recordFrame.dispose();
		}
	}
	
	public void refreshRecordView()
	{
		try
		{
			editPriceList = dao.getAllRecords("Edit Price");
			RecordTableModel editPriceModel = new RecordTableModel(editPriceList);
			editPriceTable.setModel(editPriceModel);
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(1));
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(5));
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(5));
			editPriceTable.removeColumn(editPriceTable.getColumnModel().getColumn(5));
			for(int i = 0; i < editPriceTable.getColumnCount(); i++)
			{
				editPriceTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
			
			deleteInventoryList = dao.getAllRecords("Delete Inventory");
			RecordTableModel deleteInventoryModel = new RecordTableModel(deleteInventoryList);
			deleteInventoryTable.setModel(deleteInventoryModel);
			for(int i = 0; i < 5; i++)
			{
				deleteInventoryTable.removeColumn(deleteInventoryTable.getColumnModel().getColumn(1));
			}
			for(int i = 0; i < deleteInventoryTable.getColumnCount(); i++)
			{
				deleteInventoryTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
			
			debitTransList = dao.getAllRecords("Debit Transaction");
			RecordTableModel debitTransModel = new RecordTableModel(debitTransList);
			debitTransTable.setModel(debitTransModel);
			for(int i = 0; i < 6; i++)
			{
				debitTransTable.removeColumn(debitTransTable.getColumnModel().getColumn(2));
			}
			for(int i = 0; i < debitTransTable.getColumnCount(); i++)
			{
				debitTransTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "Error: " + e1, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}