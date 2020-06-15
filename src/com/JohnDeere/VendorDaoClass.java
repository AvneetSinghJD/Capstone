package com.JohnDeere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VendorDaoClass {
	
	public boolean loginAuthorization(String userName, String password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select Username,Password from VendorLogin where UserName='" + userName
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
}
