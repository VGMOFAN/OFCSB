package core;

/*
 * Description: the Menu porttion
 * Author: Suveatha 
 * Date: Nov. 6 2018
 */

public class Menu extends Product
{
	private String category, material;
	private double price, employeePrice;
	private int sold;
	
	public Menu(int id, String n, String t, String c, String m, double d, double e, int s) 
	{
		super(id, n, t);
		setCategory(c);
		setMaterial(m);
		setPrice(d);
		setEmployeePrice(e);
		setSold(s);
	}
	
	public Menu(int id, String n, String t, String c, String m, double p, int s) 
	{
		this(id, n, t, c, m, p, 0, s);
	}
	
	public Menu(int id, String n, String t, String c, String m, double p)
	{
		this(id, n, t, c, m, p, 0, 0);
	}
	
	public Menu(String n, String t, String c, String m, double p, double emp)
	{
		this(0, n, t, c, m, p, emp, 0);
	}
	
	public void setCategory(String c)
	{
		category = c;
	}
	
	public void setMaterial(String m)
	{
		material = m;
	}
	
	public void setEmployeePrice(double ep) 
	{
		employeePrice = ep;
	}

	public void setPrice(double p) 
	{
		price = p;
	}
	
	public void setSold(int s)
	{
		sold = s;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public String getMaterial()
	{
		return material;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public double getEmployeePrice()
	{
		return employeePrice;
	}
	
	public int getSold()
	{
		return sold;
	}
}