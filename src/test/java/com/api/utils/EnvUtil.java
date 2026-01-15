package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;

public class EnvUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
	private static Dotenv dotenv;
	
	static {
		LOGGER.info("Loading the .env file....");
		dotenv = Dotenv.load();
	}
	
	private EnvUtil() {
	}
	
	@Step("Retrieving secrets from the .env file")
	public static String getValue(String keyName) {
		LOGGER.info("Reading the value from .env for the given key {}",keyName);
		return dotenv.get(keyName);
	}

}
