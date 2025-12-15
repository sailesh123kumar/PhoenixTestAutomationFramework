package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");

	private static final int MAX_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAX_POOL_SIZE"));
	private static final int MINIMUM_IDLE_TIME_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_TIME_IN_SECS"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int IDLE_TIMEOUT_IN_SECS = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SECS"));
	private static final int MAX_LIFETIME_IN_MINS = Integer.parseInt(ConfigManager.getProperty("MAX_LIFETIME_IN_MINS"));
	private static final String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");

	private static HikariConfig hikariConfig;
	private static volatile HikariDataSource hikariDataSource;
	// All threads will be aware of it

	private DatabaseManager() {

	}

	private static void initializePool() {

		if (hikariDataSource == null) { // First check for all parallel threads enter
			synchronized (DatabaseManager.class) {
				if (hikariDataSource == null) { // Only checks for the first connection request

					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USERNAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAX_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_TIME_IN_SECS);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setMaxLifetime(MAX_LIFETIME_IN_MINS * 60 * 1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);
					hikariDataSource = new HikariDataSource(hikariConfig);
				}
			}

		}
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			if (hikariDataSource == null) {
				initializePool();
			} else if (hikariDataSource.isClosed()) {
				throw new SQLException("HIKARI DATA SOURCE IS CLOSED");
			}

			connection = hikariDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
