package core;

/*
 * Author: Alex Zhou
 * Date: 2018.11.06
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Supply extends Product
{
	private int lowStock, amount;
	private String exDates;
	
	public Supply(int id, String n, String t, int ls, int a, String ed)
	{
		super(id, n, t);
		setLowStock(ls);
		setAmount(a);
		setExDates(ed);
	}
	
	public Supply(String n, String t, int ls)
	{
		this(0, n, t, ls, 0, "");
	}
	
	public void addAmount(int a)
	{
		amount += a;
	}
	
	public void addExDate(String exdate)
	{
		exDates += exdate;
	}
	
	public boolean checkCloseToExpiration() throws ParseException
	{
		if(exDates.compareTo("") == 0)
		{
			return false;
		}
		else
		{
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy MM dd");
			String string1 = exDates.substring(0, exDates.indexOf("&"));
			
			LocalDate localDate = LocalDate.now();
			String string2 = DateTimeFormatter.ofPattern("yyyy MM dd").format(localDate);
			
			Date date1 = myFormat.parse(string1);
			Date date2 = myFormat.parse(string2);
			long diff = date1.getTime() - date2.getTime();
			int days = (int) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1);
			
			if(days <= 13)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public void deleteClosestExDate()
	{
		exDates = exDates.substring(exDates.indexOf("&") + 1);
	}
	
	public void setAmount(int a)
	{
		amount = a;
	}
	
	public void setLowStock(int ls)
	{
		lowStock = ls;
	}
	
	public void setExDates(String ed)
	{
		exDates = ed;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public int getLowStock()
	{
		return lowStock;
	}
	
	public String getExDates()
	{
		return exDates;
	}
	
	public String getClosestExDate()
	{
		if(exDates.compareTo("") == 0)
		{
			return "";
		}
		else
		{
			String month1, day1;
			String date[] = exDates.substring(0, exDates.indexOf("&")).split("\\s+");
			int year = Integer.parseInt(date[0]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[2]);
			
			if(month < 10)
			{
				month1 = "0" + month;
			}
			else
			{
				month1 = "" + month;
			}
			
			if(day < 10)
			{
				day1 = "0" + day;
			}
			else
			{
				day1 = "" + day;
			}
			
			return (year + "-" + month1 + "-" + day1);
		}
	}
}