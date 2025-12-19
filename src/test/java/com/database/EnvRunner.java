package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {
	
	
	public static void main(String[] args) {
		
		Dotenv dotenv = Dotenv.load();
		System.out.println(dotenv.get("DB_URL"));
		System.out.println(dotenv.get("DB_USERNAME"));
		System.out.println(dotenv.get("DB_PASSWORD"));
		
	}

}
