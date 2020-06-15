package com.JohnDeere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoClass {

	public boolean loginAuthorization(String userName, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select Username,Password from AdminLogin where UserName='" + userName
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
	
	public List<Product> productList()throws exception_class
	{
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
		
		Statement stmt=con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from ProductList"); 
        
        ArrayList<Product> list=new ArrayList<Product>();
	            while(rs.next())
	            {
	            	  Product pro=new Product();
	            	  pro.setProductId(rs.getString(1));    
	            	  pro.setProductName(rs.getString(2));    
	            	  pro.setPrice(rs.getInt(3));    
	            	  pro.setAvailability(rs.getString(4));
	                  
	                  list.add(pro);
	                  
	            } 
	            
	            con.close();    
	            return list;
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
	
  }

	public boolean addNewProduct(String productId, String productName, int price, String availability) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			PreparedStatement stmt= con.prepareStatement("INSERT INTO ProductList VALUES(?,?,?,?)");
			stmt.setString(1, productId);
			stmt.setString(2, productName);
			stmt.setInt(3, price);
			stmt.setString(4, availability);
			
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
			e.printStackTrace();
			return false;
		}
}

	public boolean deleteProduct(String productId) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			PreparedStatement stmt= con.prepareStatement("DELETE FROM ProductList where ProductId=?");
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

	public List<Product> editProduct(String productId) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			Statement stmt=con.createStatement();
	        ResultSet rs = stmt.executeQuery("select * from ProductList where ProductId='"+productId+"'"); 
	        
	        ArrayList<Product>list=new ArrayList<Product>();
	        while(rs.next())
	        {
	        	  Product pro=new Product();
	        	  pro.setProductId(rs.getString(1));    
	        	  pro.setProductName(rs.getString(2));    
	        	  pro.setPrice(rs.getInt(3));    
	        	  pro.setAvailability(rs.getString(4));
	              
	              list.add(pro);
	        } 
	        
	        con.close();
	        return list;         
		}
		catch(Exception e)
		{
			e.printStackTrace();
				}
		return null;
	  }

	public boolean updateProduct(String productName, int price, String availability,String productId) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			
			PreparedStatement stmt= con.prepareStatement("UPDATE ProductList Set ProductName=?,Price=?,Availability=? where ProductId=?");
			stmt.setString(1, productName);
			stmt.setInt(2, price);
			stmt.setString(3, availability);
			stmt.setString(4, productId);
			
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
			e.printStackTrace();
			return false;
		}
	 }
	
}
