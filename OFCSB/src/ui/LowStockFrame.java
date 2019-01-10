package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.11.20
 */

import core.Supply;
import core.Other;
import dao.ProductDAO;
import tms.SupplyTableModel;
import tms.OtherTableModel;

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

public class LowStockFrame extends JFrame implements ActionListener
{
	private ProductDAO dao;
	private JFrame lowStockFrame;
	private JPanel lowStockPanel;
	private JScrollPane supplyPane, otherPane;
	private JButton okBtn;
	
	public LowStockFrame(ProductDAO theDAO)
	{
		dao = theDAO;
		
		//the image that is needed for the buttons
		ImageIcon buttonImage = new ImageIcon("Images/Button.png");
				
		//the font for buttons
		Font buttonFont = new Font("Arial", Font.PLAIN, 30);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
		
		lowStockFrame = new JFrame("Low-stocks");
		lowStockFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		lowStockFrame.setPreferredSize(new Dimension(650,500));
		lowStockFrame.setLayout(new BorderLayout());
		lowStockFrame.setResizable(false);
		
		lowStockPanel = new JPanel();
		lowStockPanel.setLayout(null);
		
		JTable supplyTable = new JTable();
		try
		{
			List<Supply> supplyList = dao.getLowStockSupplies();
			SupplyTableModel supplyModel = new SupplyTableModel(supplyList);
			supplyTable.setModel(supplyModel);
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(lowStockFrame, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < supplyTable.getColumnCount(); i++)
		{
			supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		JTable otherTable = new JTable();
		try
		{
			List<Other> otherList = dao.getLowStockOthers();
			OtherTableModel otherModel = new OtherTableModel(otherList);
			otherTable.setModel(otherModel);
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(lowStockFrame, "Error creating table: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		for(int i = 0; i < otherTable.getColumnCount(); i++)
		{
			otherTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		supplyPane = new JScrollPane();
		supplyPane.setBounds(10, 10, 500, 150);
		supplyPane.setViewportView(supplyTable);
		lowStockPanel.add(supplyPane);
		
		otherPane = new JScrollPane();
		otherPane.setBounds(10, 170, 500, 150);
		otherPane.setViewportView(otherTable);
		lowStockPanel.add(otherPane);
		
		okBtn = new JButton(buttonImage);
		okBtn.setBounds(250, 440, 120, 33);
		okBtn.setText("OK");
		okBtn.setFont(buttonFont);
		okBtn.setHorizontalTextPosition(JButton.CENTER);
		okBtn.setVerticalTextPosition(JButton.CENTER);
		okBtn.setContentAreaFilled(false);
		okBtn.setBorderPainted(false);
		okBtn.addActionListener(this);
		lowStockPanel.add(okBtn);

		lowStockFrame.add(lowStockPanel);
		lowStockFrame.pack();
		lowStockFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == okBtn)
		{
			lowStockFrame.dispose();
		}
	}
}