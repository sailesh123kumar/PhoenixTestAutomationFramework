package com.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.api.utils.AllureEnvironmentWriterUtil;

public class APITestListener implements ITestListener {

	private static final Logger LOGGER = LogManager.getLogger(APITestListener.class);
	
	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info("********************************************************************");
		LOGGER.info("====== Test ClassName {} ====== ",result.getMethod().getTestClass());
		LOGGER.info("====== Starting the : {}() ====== ",result.getName());
		LOGGER.info("====== Test Description : {} ====== ",result.getMethod().getDescription());
		LOGGER.info("====== Groups Associated {} ====== ",Arrays.toString(result.getMethod().getGroups()));
		LOGGER.info("*********************************************************************");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();
		LOGGER.info("********************************************************************");
		LOGGER.info("====== Total Duration : {} ms ======" , endTime - startTime);
		LOGGER.info("====== {} - Test Passed!!! ======",result.getName());
		LOGGER.info("*********************************************************************");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOGGER.info("********************************************************************");
		LOGGER.error("====== {} - Test FAILED!!! ======",result.getName());
		LOGGER.error("====== Error Message : {} ======" , result.getThrowable().getMessage());
		LOGGER.error(result.getThrowable());
		LOGGER.info("*********************************************************************");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("********************************************************************");
		LOGGER.info("====== {} - Test SKIPPED!!! ======",result.getName());
		LOGGER.error(result.getThrowable());
		LOGGER.info("*********************************************************************");
	}

	@Override
	public void onStart(ITestContext context) {
		LOGGER.info("====== Automation Test Execution Strats ======");
		AllureEnvironmentWriterUtil.createEnvironmentPropertiesFile();
	}

	@Override
	public void onFinish(ITestContext context) {
		LOGGER.info("====== FINISH ======");

	}
	
	

	
	
	
	
	
}
