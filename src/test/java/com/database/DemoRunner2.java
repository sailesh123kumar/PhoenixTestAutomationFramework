package com.database;

import java.sql.SQLException;

public class DemoRunner2 {

	public static void main(String[] args) throws SQLException {
		DatabaseManager.createConnection();
		long startTime = System.currentTimeMillis();
		
		for(int i=1;i<=1200; i++) {
			DatabaseManager.createConnection();
			DatabaseManager.createConnection();
			DatabaseManager.createConnection();
			DatabaseManager.createConnection();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + " /ms");

	}
	
}
