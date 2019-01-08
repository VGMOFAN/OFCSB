package ui;

import java.sql.SQLException;

import core.Record;
import dao.ProductDAO;

public class test
{
	public static void main(String[] args)
	{
		ProductDAO dao = null;
		try {
			dao = new ProductDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Record a = new Record(0, "Edit Price", "2019/10/11", "nestea", 1, 3);
		try {
			dao.addRecord(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
