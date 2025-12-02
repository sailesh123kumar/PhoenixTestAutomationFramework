package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil2;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JSONReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {

	// DataProviders need to return
	// Object []
	// Object [] []
	// Iterator<Object>

	@DataProvider(name = "LoginAPIDataProvider", parallel = false)
	public static Iterator<UserBean> loginAPIDataProvider() {
		Iterator<UserBean> data = CSVReaderUtil.loadCSV("testData/logInTestData.csv", UserBean.class);
		return data;
	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = false)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		Iterator<CreateJobBean> createJbBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);
		ArrayList<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		while (createJbBeanIterator.hasNext()) {
			tempBean = createJbBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}
	
	
	@DataProvider(name = "createJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount" , "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
				
		Iterator<CreateJobPayload> fakeCreateJobPayload = FakerDataGenerator.generateFakeCreateJobPayload(fakerCountInt);
		return fakeCreateJobPayload;
	}
	
	
	@DataProvider(name = "LoginAPIJSONDataProvider", parallel = false)
	public static Iterator<UserCredentials> loginAPIJSONDataProvider() {
		Iterator<UserCredentials> iterator = JSONReaderUtil.loadJSON("testData/loginAPITestData.json", UserCredentials[].class);
		return iterator;
	}
	
	@DataProvider(name = "createJobAPIJSONDataProvider", parallel = false)
	public static Iterator<CreateJobPayload> createJobAPIJSONDataProvider() {
		Iterator<CreateJobPayload> iterator = JSONReaderUtil.loadJSON("testData/createJobAPIData.json",CreateJobPayload[].class);
		return iterator;
	}
	
	
	@DataProvider(name = "loginAPIExcelDataProvider", parallel = false)
	public static Iterator<UserCredentials> loginAPIExcelDataProvider() {
		Iterator<UserCredentials> testData = ExcelReaderUtil2.loadTestData();
		return testData;
	}
	

}
