package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.api.utils.VaultDBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

	/*
	 * private static final String DB_URL = EnvUtil.getValue("DB_URL"); private
	 * static final String DB_USERNAME = EnvUtil.getValue("DB_USER_NAME"); private
	 * static final String DB_PASSWORD = EnvUtil.getValue("DB_PASSWORD");
	 * 
	 * private static final String DB_URL = VaultDBConfig.getSecret("DB_URL");
	 * private static final String DB_USERNAME =
	 * VaultDBConfig.getSecret("DB_USER_NAME"); private static final String
	 * DB_PASSWORD = VaultDBConfig.getSecret("DB_PASSWORD");
	 */

	private static boolean isVaultUp = true;
	private static final String DB_URL = loadSecret("DB_URL");
	private static final String DB_USERNAME = loadSecret("DB_USER_NAME");
	private static final String DB_PASSWORD = loadSecret("DB_PASSWORD");
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

	public static String loadSecret(String key) {
		String value = null;

		if (isVaultUp) {
			value = VaultDBConfig.getSecret(key);
			if (value == null) {
				System.err.println("Something went wrong and Vault is down");
				isVaultUp = false;

			} else {
				System.out.println("Fetching the value from Vault");
				return value;
			}
		}
		value = EnvUtil.getValue(key);
		return value;
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
