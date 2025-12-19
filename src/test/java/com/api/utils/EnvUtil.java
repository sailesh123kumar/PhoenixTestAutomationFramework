package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	
	private EnvUtil() {
		
	}
	
	public static String getValue(String keyName) {
		
		Dotenv dotenv = Dotenv.load();
		return dotenv.get(keyName);
		
	}

}
