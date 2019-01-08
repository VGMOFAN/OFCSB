package dao;

/*
 * Author: Alex Zhou
 * Date: 2018.11.07
 */

import core.Supply;
import core.Menu;
import core.Other;
import core.Record;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.BasicTextEncryptor;

public class ProductDAO
{
	private Connection myConn;
	
	//constructor
	public ProductDAO() throws Exception
	{
		//read properties from the properties file
		Properties props = new Properties();
		props.load(new FileInputStream("info/config.properties"));
		
		String dbUrlEncrypted = props.getProperty("dbUrl");
		String userEncrypted = props.getProperty("user");
		String passwordEncrypted = props.getProperty("password");
		
		BasicTextEncryptor cryptor = new BasicTextEncryptor();
		cryptor.setPassword("1");
		
		String dbUrl = cryptor.decrypt(dbUrlEncrypted);
		String user = cryptor.decrypt(userEncrypted);
		String password = cryptor.decrypt(passwordEncrypted);
		
		//create connection
		myConn = DriverManager.getConnection(dbUrl, user, password);
	}
	
	//add the information of a Supply item to the database
	public void addSupply(Supply theSupply) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("INSERT INTO Supply (name, type, low_stock, amount, expiration_dates) VALUES (?, ?, ?, ?, ?)");
			
			myPpSt.setString(1, theSupply.getName());
			myPpSt.setString(2, theSupply.getType());
			myPpSt.setInt(3, theSupply.getLowStock());
			myPpSt.setInt(4, theSupply.getAmount());
			myPpSt.setString(5, theSupply.getExDates());
			
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//add the information of a Menu item to the database
	public void addMenu(Menu theMenu) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("INSERT INTO Menu (name, type, category, material, price, employee_price, sold) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			myPpSt.setString(1, theMenu.getName());
			myPpSt.setString(2, theMenu.getType());
			myPpSt.setString(3, theMenu.getCategory());
			myPpSt.setString(4, theMenu.getMaterial());
			myPpSt.setDouble(5, theMenu.getPrice());
			myPpSt.setDouble(6, theMenu.getEmployeePrice());
			myPpSt.setInt(7, theMenu.getSold());
			
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//add the information of a Other item to the database
	public void addOther(Other theOther) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("INSERT INTO Other (name, type, low_stock, amount) VALUES (?, ?, ?, ?)");
			
			myPpSt.setString(1, theOther.getName());
			myPpSt.setString(2, theOther.getType());
			myPpSt.setInt(3, theOther.getLowStock());
			myPpSt.setInt(4, theOther.getAmount());
			
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//update the information of a Supply item existed in the database according to the id
	public void updateSupply(Supply theSupply) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("UPDATE Supply SET name=?, low_stock=?, amount=?, expiration_dates=? WHERE id=?");
			
			myPpSt.setString(1, theSupply.getName());
			myPpSt.setInt(2, theSupply.getLowStock());
			myPpSt.setInt(3, theSupply.getAmount());
			myPpSt.setString(4, theSupply.getExDates());
			myPpSt.setInt(5, theSupply.getID());
			
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//update the information of a Menu item existed in the database according to the id
	public void updateMenu(Menu theMenu) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("UPDATE Menu SET name=?, category=?, material=?, price=?, employee_price=?, sold=? WHERE id=?");
			
			myPpSt.setString(1, theMenu.getName());
			myPpSt.setString(2, theMenu.getCategory());
			myPpSt.setString(3, theMenu.getMaterial());
			myPpSt.setDouble(4, theMenu.getPrice());
			myPpSt.setDouble(5, theMenu.getEmployeePrice());
			myPpSt.setInt(6, theMenu.getSold());
			myPpSt.setInt(7, theMenu.getID());
			
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//update the information of a Other item existed in the database according to the id
	public void updateOther(Other theOther) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("UPDATE Other SET name=?, low_stock=?, amount=? WHERE id=?");
			
			myPpSt.setString(1, theOther.getName());
			myPpSt.setInt(2, theOther.getLowStock());
			myPpSt.setInt(3, theOther.getAmount());
			myPpSt.setInt(4, theOther.getID());
			
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//return a list that contains all the Supply items that existed in the database
	public List<Supply> getAllSupplies() throws Exception
	{
		List<Supply> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Supply");
			
			while(myRs.next())
			{
				Supply temp = convertRowToSupply(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//return a list that contains all the Menu items that existed in the database
	public List<Menu> getAllMenus() throws SQLException
	{
		List<Menu> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Menu");
			
			while(myRs.next())
			{
				Menu temp = convertRowToMenu(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//return a list that contains all the Other items that existed in the database
	public List<Other> getAllOthers() throws SQLException
	{
		List<Other> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Other");
			
			while(myRs.next())
			{
				Other temp = convertRowToOther(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//return a list that contains the names of all Supply items in the database
	public String[] getSupplyNames() throws SQLException
	{
		List<String> list = new ArrayList<>();
		list.add("");
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Supply");
			
			while(myRs.next())
			{
				Other temp = convertRowToOther(myRs);
				list.add(temp.getName());
			}
			
			String[] names = new String[list.size()];
			names = list.toArray(names);
			
			return names;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//return a list that contains all the Supply items that are in low-stock situation
	public List<Supply> getLowStockSupplies() throws SQLException
	{
		List<Supply> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Supply WHERE amount < low_stock");
			
			while(myRs.next())
			{
				Supply temp = convertRowToSupply(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//return a list that contains all the Other items that are in low-stock situation
	public List<Other> getLowStockOthers() throws SQLException
	{
		List<Other> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Other WHERE amount < low_stock");
			
			while(myRs.next())
			{
				Other temp = convertRowToOther(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//return a list that contains all the Supply items whose 'name' contains the String that is wanted
	public List<Supply> searchSupply(String name) throws SQLException
	{
		List<Supply> list = new ArrayList<>();
		PreparedStatement myPpSt = null;
		ResultSet myRs = null;

		name += "%";
		
		try
		{
			myPpSt = myConn.prepareStatement("SELECT * FROM Supply WHERE name LIKE ? ORDER BY name");
			
			myPpSt.setString(1, name);
			
			myRs = myPpSt.executeQuery();
			
			while(myRs.next())
			{
				Supply temp = convertRowToSupply(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(myPpSt, myRs);
		}
	}
	
	////return a list that contains all the Menu items whose 'name' contains the String that is wanted
	public List<Menu> searchMenu(String name) throws SQLException
	{
		List<Menu> list = new ArrayList<>();
		PreparedStatement myPpSt = null;
		ResultSet myRs = null;
		
		try
		{
			name += "%";
			
			myPpSt = myConn.prepareStatement("SELECT * FROM Menu WHERE name LIKE ? ORDER BY name");
			
			myPpSt.setString(1, name);
			
			myRs = myPpSt.executeQuery();
			
			while(myRs.next())
			{
				Menu temp = convertRowToMenu(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(myPpSt, myRs);
		}
	}
	
	//return a list that contains all the Other items whose 'name' contains the String that is wanted
	public List<Other> searchOther(String name) throws SQLException
	{
		List<Other> list = new ArrayList<>();
		PreparedStatement myPpSt = null;
		ResultSet myRs = null;
		
		try
		{
			name += "%";
			
			myPpSt = myConn.prepareStatement("SELECT * FROM Other WHERE name LIKE ? ORDER BY name");
			
			myPpSt.setString(1, name);
			
			myRs = myPpSt.executeQuery();
			
			while(myRs.next())
			{
				Other temp = convertRowToOther(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(myPpSt, myRs);
		}
	}
	
	//return a list that contains all the Supply item which are close to expiration date
	public List<Supply> getCloseToExpirationSupplies() throws SQLException, ParseException
	{
		List<Supply> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Supply");
			
			while(myRs.next())
			{
				Supply temp = convertRowToSupply(myRs);
				if(temp.checkCloseToExpiration())
				{
					list.add(temp);
				}
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//decrease the 'amount' of Supply items which are the 'material's of a Menu item
	public void decreaseInventory(Menu theMenu) throws SQLException
	{
		PreparedStatement myPpSt = null;
		String[] materials = theMenu.getMaterial().split("&");
		
		try
		{
			for(int i = 0; i < materials.length; i++)
			{
				List<Supply> temp = searchSupply(materials[i]);
				int id = temp.get(0).getID();
				myPpSt = myConn.prepareStatement("UPDATE Supply SET amount=amount-1 WHERE id=?");
				myPpSt.setInt(1, id);
				myPpSt.executeUpdate();
			}
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	public void decreaseInventory(Supply theSupply, int amount) throws SQLException
	{
		PreparedStatement myPpSt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("UPDATE Supply SET amount=? WHERE id=?");
			myPpSt.setInt(1, theSupply.getAmount() - amount);
			myPpSt.setInt(2, theSupply.getID());
			myPpSt.executeUpdate();
		}
		finally
		{
			close(myPpSt);
		}
	}
	
	//delete a Product from the database depends on its type and id
	public void deleteProduct(String type, int id) throws SQLException
	{
		PreparedStatement myPpSt = null;
		Statement mySt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("DELETE FROM " + type + " WHERE id=?");
			myPpSt.setInt(1, id);
			myPpSt.executeUpdate();
			
			mySt = myConn.createStatement();
			mySt.execute("ALTER TABLE " + type + " DROP id");
			mySt.execute("ALTER TABLE " + type + " ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY");
		}
		finally
		{
			close(myPpSt);
			close(mySt);
		}
	}
	
	//get the last id in a table in the database
	public int getLastID(String type) throws SQLException
	{
		int lastID = 0;
		Statement mySt = null;
		ResultSet myRs = null;
		
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM " + type);
			
			while(myRs.next())
			{
				lastID++;
			}
			
			return lastID;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//add a Record to the database
	public void addRecord(Record theRecord) throws SQLException
	{
		PreparedStatement myPpSt1 = null;
		PreparedStatement myPpSt2 = null;
		
		try
		{
			if(theRecord.getType().compareTo("Delete Inventory") == 0)
			{
				myPpSt1 = myConn.prepareStatement("INSERT INTO Record (type, date, menu_item, edited_price, original_price, difference, supply_item, reason, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				myPpSt1.setString(1, theRecord.getType());
				myPpSt1.setString(2, theRecord.getDate());
				myPpSt1.setString(3, theRecord.getMenuItem());
				myPpSt1.setDouble(4, theRecord.getEditedPrice());
				myPpSt1.setDouble(5, theRecord.getOriginalPrice());
				myPpSt1.setDouble(6, theRecord.getDifference());
				myPpSt1.setString(7, theRecord.getSupplyItem());
				myPpSt1.setString(8, theRecord.getReason());
				myPpSt1.setInt(9, theRecord.getAmount());
				
				List<Supply> temp = searchSupply(theRecord.getSupplyItem());
				int id = temp.get(0).getID();
				myPpSt2 = myConn.prepareStatement("UPDATE Supply SET amount=amount-? WHERE id=?");
				myPpSt2.setInt(1, theRecord.getAmount());
				myPpSt2.setInt(2, id);
				myPpSt2.executeUpdate();
				
				myPpSt1.executeUpdate();
				myPpSt2.executeUpdate();
			}
			else
			{
				myPpSt1 = myConn.prepareStatement("INSERT INTO Record (type, date, menu_item, edited_price, original_price, difference, supply_item, reason, amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				myPpSt1.setString(1, theRecord.getType());
				myPpSt1.setString(2, theRecord.getDate());
				myPpSt1.setString(3, theRecord.getMenuItem());
				myPpSt1.setDouble(4, theRecord.getEditedPrice());
				myPpSt1.setDouble(5, theRecord.getOriginalPrice());
				myPpSt1.setDouble(6, theRecord.getDifference());
				myPpSt1.setString(7, theRecord.getSupplyItem());
				myPpSt1.setString(8, theRecord.getReason());
				myPpSt1.setInt(9, theRecord.getAmount());
				
				myPpSt1.executeUpdate();
			}
		}
		finally
		{
			close(myPpSt1);
			close(myPpSt2);
		}
	}
	
	public List<Record> getAllRecords(String t) throws SQLException
	{
		List<Record> list = new ArrayList<>();
		
		Statement mySt = null;
		ResultSet myRs = null;
		
		String type = "'" + t + "'";
		try
		{
			mySt = myConn.createStatement();
			myRs = mySt.executeQuery("SELECT * FROM Record WHERE type=" + type);
			
			while(myRs.next())
			{
				Record temp = convertRowToRecord(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally
		{
			close(mySt, myRs);
		}
	}
	
	//delete a Record from the database depends on its id
	public void deleteRecord(int id) throws SQLException
	{
		PreparedStatement myPpSt = null;
		Statement mySt = null;
		
		try
		{
			myPpSt = myConn.prepareStatement("DELETE FROM Record WHERE id=?");
			myPpSt.setInt(1, id);
			myPpSt.executeUpdate();
			
			mySt = myConn.createStatement();
			mySt.execute("ALTER TABLE Record DROP id");
			mySt.execute("ALTER TABLE Record ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY");
		}
		finally
		{
			close(myPpSt);
			close(mySt);
		}
	}
	
	//clear all the Records which have a certain 'type'
	public void clearAllRecord(String t) throws SQLException
	{
		Statement mySt = null;
		String type = "'" + t + "'";

		System.out.println(type);
		try
		{
			mySt = myConn.createStatement();
			mySt.execute("DELETE FROM Record WHERE type=" + type);
			mySt.execute("ALTER TABLE Record DROP id");
			mySt.execute("ALTER TABLE Record ADD id INT NOT NULL AUTO_INCREMENT PRIMARY KEY");
		}
		finally
		{
			close(mySt);
		}
	}
	
	//convert a row in the database into a Supply object
	private Supply convertRowToSupply(ResultSet myRs) throws SQLException
	{
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String type = myRs.getString("type");
		int lowStock = myRs.getInt("low_stock");
		int amount = myRs.getInt("amount");
		String exDates = myRs.getString("expiration_dates");
		
		Supply tempSupply = new Supply(id, name, type, lowStock, amount, exDates);
		return tempSupply;
	}
	
	//convert a row in the database into a Menu object
	private Menu convertRowToMenu(ResultSet myRs) throws SQLException
	{
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String type = myRs.getString("type");
		String category = myRs.getString("category");
		String material = myRs.getString("material");
		double price = myRs.getDouble("price");
		double emPrice = myRs.getDouble("employee_price");
		int sold = myRs.getInt("sold");
		
		Menu tempMenu = new Menu(id, name, type, category, material, price, emPrice, sold);
		return tempMenu;
	}
	
	//convert a row in the database into an Other object
	private Other convertRowToOther(ResultSet myRs) throws SQLException
	{
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String type = myRs.getString("type");
		int lowStock = myRs.getInt("low_stock");
		int amount = myRs.getInt("amount");
		
		Other tempOther = new Other(id, name, type, lowStock, amount);
		return tempOther;
	}
	
	//convert a row in the database into a Record object
	private Record convertRowToRecord(ResultSet myRs) throws SQLException
	{
		int id = myRs.getInt("id");
		String type = myRs.getString("type");
		String menuItem = myRs.getString("menu_item");
		String supplyItem = myRs.getString("supply_item");
		String date = myRs.getString("date");
		double editedPrice = myRs.getDouble("edited_price");
		double originalPrice = myRs.getDouble("original_price");
		String reason = myRs.getString("reason");
		int amount = myRs.getInt("amount");
		int transNum = myRs.getInt("transaction_number");
		
		Record tempRecord = new Record(id, type, date, menuItem, editedPrice, originalPrice, supplyItem, reason, amount, transNum);
		return tempRecord;
	}
	
	//close the connection, statement and resultset
	private void close(Connection myConn, Statement mySt, ResultSet myRs) throws SQLException
	{
		if (myRs != null)
		{
			myRs.close();
		}

		if (mySt != null)
		{
			mySt.close();
		}
		
		if (myConn != null)
		{
			myConn.close();
		}
	}
	
	//constructor of close method
	private void close(Statement mySt, ResultSet myRs) throws SQLException
	{
		close(null, mySt, myRs);		
	}
	
	//constructor of close method
	private void close(Statement mySt) throws SQLException
	{
		close(null, mySt, null);
	}
}