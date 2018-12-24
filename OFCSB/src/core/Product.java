package core;

/*
 * Author: Alex Zhou
 * Date: 2018.10.29
 */

public class Product
{
	private String name, type;
	private int ID;
	public Product(int id, String n, String t)
	{
		ID = id;
		name = n;
		type = t;
	}
	
	public void setID(int id)
	{
		ID = id;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
}