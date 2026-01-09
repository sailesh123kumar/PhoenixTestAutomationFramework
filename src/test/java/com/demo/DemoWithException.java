package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoWithException {

	private static Logger logger = LogManager.getLogger(DemoWithException.class);

	public static void main(String[] args) {

		logger.info("===PROGRAM STARTED===");
		int a = 10;
		logger.info("Value of a is {} ", a);
		int b = 0;

		if (b == 0) {
			logger.warn("Value of b is {} ", b);
		} else {
			logger.info("Value of b is {} ", b);
		}

		int result = 0;
		try {
			result = a / b;
			logger.info("final result is {} ", result);
			logger.info("===PROGRAM ENDED===");
		} catch (ArithmeticException e) {
			logger.error("Operation cannot happen ", e);
		}

	}

}
