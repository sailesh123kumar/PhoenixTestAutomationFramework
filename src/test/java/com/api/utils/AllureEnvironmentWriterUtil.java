package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtil {

	private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);
	
	public static void createEnvironmentPropertiesFile()  {
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdirs();
		
		Properties prop = new Properties();
		prop.setProperty("Project Name", "Phoenix test Automation Framework");
		prop.setProperty("Owner Name", "Sailesh Kumar");
		prop.setProperty("Environment", ConfigManager.env);
		prop.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));
		prop.setProperty("Operating System", System.getProperty("os.name"));
		prop.setProperty("OS Version", System.getProperty("os.version"));
		prop.setProperty("JDK Version", System.getProperty("java.version"));
		
		
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(folderPath+"/environment.properties");
			prop.store(fileWriter, "My Properties File");
			LOGGER.info("Created the environment.properties file at {} ",folderPath);
		} catch (IOException e) {
			LOGGER.error("Inable to create the environment.properties file ",e);
			e.printStackTrace();
		}
		

	}

}
