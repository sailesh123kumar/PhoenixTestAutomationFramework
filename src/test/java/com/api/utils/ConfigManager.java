package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static String path = "config/config.properties";
	private static Properties prop = new Properties();
	private static String env;

	private ConfigManager() {
	}

	/**
	 * static block will be executed only once when the class loads This block helps
	 * us to loading the file in the memory
	 */
	static {

		env = System.getProperty("env", "qa");
		System.out.println("Test Execution running in the " + env.toUpperCase() + " environment");

		switch (env.toLowerCase().trim()) {

		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";
		}

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (inputStream == null) {
			throw new RuntimeException("===Cannot Find the file at the path===" + path);
		}

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
