package tms;

/*
 * Author: Alex Zhou
 * Date: 2018.11.29
 */

import core.Menu;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MenuTableModel extends AbstractTableModel
{
	private static final int NAME_COL = 0;
	private static final int CATEGORY_COL = 1;
	private static final int PRICE_COL = 2;
	private static final int EMPRICE_COL = 3;
	private static final int SOLD_COL = 4;
	private String[] columnNames = {"Name", "Category", "Price", "Employee price", "Sold today"};
	private List<Menu> menu;
	
	public MenuTableModel(List<Menu> theMenu)
	{
		menu = theMenu;
	}
	
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return menu.size();
	}

	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Menu tempMenu = menu.get(row);

		switch (col)
		{
		case NAME_COL:
			return tempMenu.getName();
		case CATEGORY_COL:
			return tempMenu.getCategory();
		case PRICE_COL:
			return tempMenu.getPrice();
		case EMPRICE_COL:
			return tempMenu.getEmployeePrice();
		case SOLD_COL:
			return tempMenu.getSold();
		default:
			return tempMenu.getName();
		}
	}

	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}