package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner2 {

	public static void main(String[] args) throws SQLException {
		Connection connection = DatabaseManager.getConnection();
		System.out.println(connection);
		
		long startTime = System.currentTimeMillis();

		for (int i = 1; i <= 1200; i++) {
			DatabaseManager.getConnection();
			DatabaseManager.getConnection();
		}

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + " /ms");
	}
}
