package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
	
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);
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

		LOGGER.info("Reading the env value passed from terminal");
		
		if(System.getProperty("env")== null) {
			LOGGER.warn("Environment variable as not passed.Hence Using QA as the env");
		}
		
		env = System.getProperty("env", "qa");
		LOGGER.info("Running the tests in the env {}",env.toUpperCase());

		switch (env.toLowerCase().trim()) {

		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";
		}

		LOGGER.info("Using the properties file from the path {}",path);
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (inputStream == null) {
			LOGGER.error("===Cannot Find the file at the path {}===" , path);
			throw new RuntimeException("===Cannot Find the file at the path===" + path);
		}

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			LOGGER.error("===Cannot Find the file at the path {}===" , path ,e);
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
