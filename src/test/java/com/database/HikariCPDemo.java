package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {
	
	public static void main(String[] args) throws SQLException {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setMaxLifetime(300000);
		hikariConfig.setPoolName("Phoenix Test Automation Framework");
		
		HikariDataSource ds = new HikariDataSource(hikariConfig);
		Connection connection = ds.getConnection();
		System.out.println(connection);
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT first_name , last_name , mobile_number   from tr_customer;");

		while(resultSet.next()) {
			System.out.print(resultSet.getString("first_name"));
			System.out.print(" | "+resultSet.getString("last_name"));
			System.out.print(" | "+resultSet.getString("mobile_number"));
			System.out.println("");
		}
		
		ds.close();
		
		
	}

}
