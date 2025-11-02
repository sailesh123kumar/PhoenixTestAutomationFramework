package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
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

}
