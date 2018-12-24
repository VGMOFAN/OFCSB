package tms;

/*
 * Author: Alex Zhou
 * Date: 2018.11.25
 */

import core.Supply;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SupplyTableModel extends AbstractTableModel
{
	private static final int NAME_COL = 0;
	private static final int LOW_STOCK_COL = 1;
	private static final int AMOUNT_COL = 2;
	private static final int EXDATE_COL = 3;
	private String[] columnNames = {"Name", "Low-stock", "Amount", "Expiration date"};
	private List<Supply> supply;
	
	public SupplyTableModel(List<Supply> theSupply)
	{
		supply = theSupply;
	}
	
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return supply.size();
	}

	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Supply tempSupply = supply.get(row);

		switch (col)
		{
		case NAME_COL:
			return tempSupply.getName();
		case LOW_STOCK_COL:
			return tempSupply.getLowStock();
		case AMOUNT_COL:
			return tempSupply.getAmount();
		case EXDATE_COL:
			return tempSupply.getClosestExDate();
		default:
			return tempSupply.getName();
		}
	}

	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}