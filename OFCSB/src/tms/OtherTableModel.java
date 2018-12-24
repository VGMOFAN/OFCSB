package tms;

/*
 * Author: Alex Zhou
 * Date: 2018.11.25
 */

import core.Other;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class OtherTableModel extends AbstractTableModel
{
	private static final int NAME_COL = 0;
	private static final int LOW_STOCK_COL = 1;
	private static final int AMOUNT_COL = 2;
	private String[] columnNames = {"Name", "Low-stock", "Amount"};
	private List<Other> other;
	
	public OtherTableModel(List<Other> theOther)
	{
		other = theOther;
	}
	
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return other.size();
	}

	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Other tempOther = other.get(row);

		switch (col)
		{
		case NAME_COL:
			return tempOther.getName();
		case LOW_STOCK_COL:
			return tempOther.getLowStock();
		case AMOUNT_COL:
			return tempOther.getAmount();
		default:
			return tempOther.getName();
		}
	}

	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}