package com.database;

import static org.testng.Assert.assertEqualsNoOrder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	
	public static void main(String[] args) throws SQLException {
		
		//Step 1: Establish the connection to the Phoenix Database
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://64.227.160.186:3306/SR_DEV", "srdev_ro_automation", "Srdev@123");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT first_name , last_name , mobile_number   from tr_customer;");
		
		int s_no = 1;
		while(resultSet.next()) {
			System.out.println(s_no+ " | " +resultSet.getString("first_name") + " | " + resultSet.getString("last_name") + " | " +  resultSet.getString("mobile_number"));
			s_no++;
		}
		
		
		
	}
}
