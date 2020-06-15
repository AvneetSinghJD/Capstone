package com.JohnDeere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CCDaoClass {
	public boolean loginAuthorization(String userName, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select Username,Password from CustomerCare where UserName='" + userName
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

	public List<CustomerProduct> customerProductList() throws exception_class {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CustomerCart order by quantity desc");

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
}
