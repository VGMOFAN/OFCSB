package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.11.20
 */

import core.Supply;
import dao.ProductDAO;
import tms.SupplyTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ExpirationFrame extends JFrame implements ActionListener
{
	private ProductDAO dao;
	private DefaultTableCellRenderer dtcr;
	private JFrame expirationFrame;
	private JPanel expirationPanel;
	private JScrollPane expirationPane;
	private JButton deleteBtn, okBtn;
	private JTable supplyTable;
	private List<Supply> supplyList;
	
	//a constructor that can inherit the dao from inventoryPanel
	public ExpirationFrame(ProductDAO theDAO)
	{
		dao = theDAO;
		
		//the image that is needed for the buttons
		ImageIcon buttonImage = new ImageIcon("Images/Button.png");
						
		//the font for buttons
		Font buttonFont = new Font("Arial", Font.PLAIN, 30);
		
		Font headerFont = new Font("Arial", Font.BOLD, 30);
		
		//a table cell renderer in order to display table values at the center of the cell
		dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		
		//settings for this frame
		expirationFrame = new JFrame("Expirations");
		expirationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		expirationFrame.setPreferredSize(new Dimension(650,500));
		expirationFrame.setLayout(new BorderLayout());
		expirationFrame.setResizable(false);
		
		expirationPanel = new JPanel();
		expirationPanel.setLayout(null);
		
		//create a table that contains all the Supply items that are close to expiration date
		supplyTable = new JTable();
		try
		{
			supplyList = dao.getCloseToExpirationSupplies();
			SupplyTableModel supplyModel = new SupplyTableModel(supplyList);
			supplyTable.setModel(supplyModel);
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(this, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		//set all the text position in columns of supplyTable to center
		for(int i = 0; i < supplyTable.getColumnCount(); i++)
		{
			supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		supplyTable.setFont(buttonFont);
		supplyTable.getTableHeader().setFont(headerFont);
		supplyTable.setRowHeight(60);
		
		//
		expirationPane = new JScrollPane();
		expirationPane.setBounds(10, 10, 500, 400);
		expirationPane.setViewportView(supplyTable);
		expirationPanel.add(expirationPane);
		
		deleteBtn = new JButton(buttonImage);
		deleteBtn.setBounds(150, 440, 280, 82);
		deleteBtn.setText("Delete");
		deleteBtn.setFont(buttonFont);
		deleteBtn.setHorizontalTextPosition(JButton.CENTER);
		deleteBtn.setVerticalTextPosition(JButton.CENTER);
		deleteBtn.setContentAreaFilled(false);
		deleteBtn.setBorderPainted(false);
		deleteBtn.addActionListener(this);
		expirationPanel.add(deleteBtn);
		
		okBtn = new JButton(buttonImage);
		okBtn.setBounds(300, 440, 280, 82);
		okBtn.setText("OK");
		okBtn.setFont(buttonFont);
		okBtn.setHorizontalTextPosition(JButton.CENTER);
		okBtn.setVerticalTextPosition(JButton.CENTER);
		okBtn.setContentAreaFilled(false);
		okBtn.setBorderPainted(false);
		okBtn.addActionListener(this);
		expirationPanel.add(okBtn);
		
		expirationFrame.add(expirationPanel);
		expirationFrame.pack();
		expirationFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == deleteBtn)
		{
			int row = supplyTable.getSelectedRow();
			
			Supply temp = supplyList.get(row);
			temp.deleteClosestExDate();
			try
			{
				dao.updateSupply(temp);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			try
			{
				supplyList = dao.getCloseToExpirationSupplies();
				SupplyTableModel supplyModel = new SupplyTableModel(supplyList);
				supplyTable.setModel(supplyModel);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(this, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			for(int i = 0; i < supplyTable.getColumnCount(); i++)
			{
				supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
			}
		}
		
		if(e.getSource() == okBtn)
		{
			expirationFrame.dispose();
		}
	}
}