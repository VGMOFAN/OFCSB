package tms;

/*
 * Author: Alex Zhou
 * Date: 2018.12.16
 */

import core.Record;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RecordTableModel extends AbstractTableModel
{
	private static final int DATE_COL = 0;
	private static final int TRANS_NUM_COL = 1;
	private static final int MENU_ITEM_COL = 2;
	private static final int ORIGINAL_PRICE_COL = 3;
	private static final int EDITED_PRICE_COL = 4;
	private static final int DIFFERENCE_COL = 5;
	private static final int SUPPLY_ITEM_COL = 6;
	private static final int REASON_COL = 7;
	private static final int AMOUNT_COL = 8;
	private String[] columnNames = {"Date", "Transaction #", "Menu Item", "Original Price", "Edited Price", "Difference", "Supply Item", "Reason", "Amount"};
	private List<Record> record;
	
	public RecordTableModel(List<Record> theRecord)
	{
		record = theRecord;
	}
	
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public int getRowCount()
	{
		return record.size();
	}

	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Record tempRecord = record.get(row);

		switch (col)
		{
		case DATE_COL:
			return tempRecord.getDate();
		case TRANS_NUM_COL:
			return tempRecord.getTransNum();
		case MENU_ITEM_COL:
			return tempRecord.getMenuItem();
		case ORIGINAL_PRICE_COL:
			return tempRecord.getOriginalPrice();
		case EDITED_PRICE_COL:
			return tempRecord.getEditedPrice();
		case DIFFERENCE_COL:
			return tempRecord.getDifference();
		case SUPPLY_ITEM_COL:
			return tempRecord.getSupplyItem();
		case REASON_COL:
			return tempRecord.getReason();
		case AMOUNT_COL:
			if(tempRecord.getType().compareTo("Debit Transaction") == 0)
			{
				return tempRecord.getDebitAmount();
			}
			else
			{
				return tempRecord.getAmount();
			}
		default:
			return tempRecord.getDate();
		}
	}

	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}