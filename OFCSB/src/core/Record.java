package core;

/*
 * Author: Alex Zhou
 * Date: 2018.12.11
 */

public class Record
{
	private int id, amount, transNum;
	private String type, menuItem, supplyItem, date, reason;
	private double editedPrice, originalPrice, difference;
	
	public Record(int i, String t, String d, String mi, double ep, double op, String si, String r, int a, int trans)
	{
		setID(i);
		setType(t);
		setDate(d);
		setMenuItem(mi);
		setEditedPrice(ep);
		setOriginalPrice(op);
		setSupplyItem(si);
		setReason(r);
		setAmount(a);
		setTransNum(trans);
		difference = op - ep;
	}
	
	//for editing price
	public Record(int i, String t, String d, String mi, double ep, double op)
	{
		this(i, t, d, mi, ep, op, "", "", 0, 0);
	}
	
	//for deleting inventory
	public Record(int i, String t, String d, String si, String r, int a)
	{
		this(i, t, d, "", 0, 0, si, r, a, 0);
	}
	
	public Record(int i, String t, String d, int a, int trans)
	{
		this(i, t, d, "", 0, 0, "", "", a, trans);
	}
	
	public void setID(int i)
	{
		id = i;
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public void setDate(String d)
	{
		date = d;
	}
	
	public void setMenuItem(String mi)
	{
		menuItem = mi;
	}
	
	public void setEditedPrice(double ep)
	{
		editedPrice = ep;
	}
	
	public void setOriginalPrice(double op)
	{
		originalPrice = op;
	}
	
	public void setSupplyItem(String si)
	{
		supplyItem = si;
	}
	
	public void setReason(String r)
	{
		reason = r;
	}
	
	public void setAmount(int a)
	{
		amount = a;
	}
	
	public void setTransNum(int trans)
	{
		transNum = trans;
	}
	
	public int getID()
	{
		return id;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getMenuItem()
	{
		return menuItem;
	}
	
	public double getEditedPrice()
	{
		return editedPrice;
	}
	
	public double getOriginalPrice()
	{
		return originalPrice;
	}
	
	public double getDifference()
	{
		return difference;
	}
	
	public String getSupplyItem()
	{
		return supplyItem;
	}
	
	public String getReason()
	{
		return reason;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public int getTransNum()
	{
		return transNum;
	}
}