package com.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.utils.CSVReaderUtil;
import com.dataproviders.api.bean.UserBean;

public class DataProvidersUtils {
	
	//DataProviders need to return
	// Object []
	// Object [] []
	//Iterator<Object>
	
	@DataProvider(name = "LoginAPIDataProvider" , parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		Iterator<UserBean> data = CSVReaderUtil.loadCSV("testData/logInTestData.csv");
		return data;
	}

}
