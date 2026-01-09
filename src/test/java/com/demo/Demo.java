package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Demo {
	
	private static Logger logger = LogManager.getLogger(Demo.class);
	
	public static void main(String[] args) {
		
		logger.info("===PROGRAM STARTED===");
		int a = 10;
		logger.info("Value of a is {} ",a);
		int b = 20;
		logger.info("Value of b is {} ",b);
		
		int result = a+b;
		logger.info("final result is {} ",result);
		logger.info("===PROGRAM ENDED===");
	}

}
