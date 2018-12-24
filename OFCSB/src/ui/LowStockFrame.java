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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LowStockFrame extends JFrame implements ActionListener
{
	private ProductDAO dao;
	private JFrame lowStockFrame;
	private JPanel lowStockPanel;
	private JScrollPane lowStockPane;
	private JButton okBtn;
	
	public LowStockFrame(ProductDAO theDAO)
	{
		dao = theDAO;
		
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
			e1.printStackTrace();
		}
		for(int i = 0; i < supplyTable.getColumnCount(); i++)
		{
			supplyTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
		
		lowStockPane = new JScrollPane();
		lowStockPane.setBounds(10, 10, 500, 400);
		lowStockPane.setViewportView(supplyTable);
		lowStockPanel.add(lowStockPane);
		
		okBtn = new JButton("OK");
		okBtn.setBounds(250, 440, 120, 33);
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