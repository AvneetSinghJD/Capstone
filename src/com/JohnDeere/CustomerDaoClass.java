package com.JohnDeere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoClass {

	public boolean loginAuthorizationCustomer(String userName, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select Username,Password from CustomerLogin where UserName='" + userName
					+ "' and Password='" + password + "'");

			boolean flag = false;
			while (rs.next()) {
				flag = true;
			}

			con.close();
			return flag;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addToCart(String productId, String productName, int price, String availability, int quantity) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			PreparedStatement stmt = con.prepareStatement("INSERT INTO CustomerCart VALUES(?,?,?,?,?)");
			stmt.setString(1, productId);
			stmt.setString(2, productName);
			stmt.setInt(3, price);
			stmt.setString(4, availability);
			stmt.setInt(5, quantity);

			int i = stmt.executeUpdate();
			con.commit();

			con.close();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CustomerProduct> customerProductList() throws exception_class {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CustomerCart");

			ArrayList<CustomerProduct> clist = new ArrayList<CustomerProduct>();
			while (rs.next()) {
				CustomerProduct cpro = new CustomerProduct();
				cpro.setProductId(rs.getString(1));
				cpro.setProductName(rs.getString(2));
				cpro.setPrice(rs.getInt(3));
				cpro.setAvailability(rs.getString(4));
				cpro.setQuantity(rs.getInt(5));

				clist.add(cpro);

			}

			con.close();
			return clist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean deleteProduct(String productId) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			PreparedStatement stmt= con.prepareStatement("DELETE FROM CustomerCart where ProductId=?");
			stmt.setString(1,productId);
			int i=stmt.executeUpdate();
			con.commit();
			
			con.close();
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
				
		}
		catch(Exception e)
		{
			return false;
		}
	}
}