package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JSONReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.database.dao.JobHeadDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {
	
	private static final Logger LOGGER = LogManager.getLogger(DataProvidersUtils.class);

	// DataProviders need to return
	// Object []
	// Object [] []
	// Iterator<Object>

	@DataProvider(name = "LoginAPIDataProvider", parallel = false)
	public static Iterator<UserBean> loginAPIDataProvider() {
		LOGGER.info("Loading the data from csv file testData/logInTestData.csv");
		Iterator<UserBean> data = CSVReaderUtil.loadCSV("testData/logInTestData.csv", UserBean.class);
		return data;
	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = false)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		LOGGER.info("Loading the data from csv file testData/CreateJobData.csv");

		Iterator<CreateJobBean> iterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv", CreateJobBean.class);

		ArrayList<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		while (iterator.hasNext()) {
			tempBean = iterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}

		System.out.println(payloadList);
		return payloadList.iterator();
	}

	@DataProvider(name = "createJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);

		LOGGER.info("Generating fake createJob data with faker count {}",fakerCount);
		Iterator<CreateJobPayload> fakeCreateJobPayload = FakerDataGenerator
				.generateFakeCreateJobPayload(fakerCountInt);
		return fakeCreateJobPayload;
	}

	@DataProvider(name = "LoginAPIJSONDataProvider", parallel = false)
	public static Iterator<UserBean> loginAPIJSONDataProvider() {
		LOGGER.info("Loading the data from JSON file testData/loginAPITestData.json");

		Iterator<UserBean> iterator = JSONReaderUtil.loadJSON("testData/loginAPITestData.json",
				UserBean[].class);
		return iterator;
	}

	@DataProvider(name = "createJobAPIJSONDataProvider", parallel = false)
	public static Iterator<CreateJobPayload> createJobAPIJSONDataProvider() {
		LOGGER.info("Loading the data from JSON file testData/createJobAPIData.json");

		Iterator<CreateJobPayload> iterator = JSONReaderUtil.loadJSON("testData/createJobAPIData.json",
				CreateJobPayload[].class);
		return iterator;
	}

	@DataProvider(name = "loginAPIExcelDataProvider", parallel = false)
	public static Iterator<UserBean> loginAPIExcelDataProvider() {
		LOGGER.info("Loading the data from Excel file testData/PhoenixTestData.xlsx from the sheet LoginTestData");

		Iterator<UserBean> testData = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "LoginTestData",
				UserBean.class);
		return testData;
	}

	@DataProvider(name = "createJobExcelDataProvider", parallel = false)
	public static Iterator<CreateJobPayload> createJobExcelDataProvider() {
		ArrayList<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		LOGGER.info("Loading the data from Excel file testData/PhoenixTestData.xlsx from the sheet CreatejobTestData");

		Iterator<CreateJobBean> createJbBeanIterator = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx",
				"CreatejobTestData", CreateJobBean.class);
		;
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		while (createJbBeanIterator.hasNext()) {
			tempBean = createJbBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}

	
	@DataProvider(name = "createJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDBDataProvider() {

		LOGGER.info("Loading data from the DataBase for createJobPayload");
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobDBData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobPayload payload;
		
		for (CreateJobBean bean : beanList) {
			payload = CreateJobBeanMapper.mapper(bean);
			payloadList.add(payload);
		}
		
		return payloadList.iterator();

	}

}
