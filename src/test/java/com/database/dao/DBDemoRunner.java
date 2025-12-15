package com.database.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DBDemoRunner {
	
	public static void main(String[] args) throws SQLException {
		
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobDBData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean bean:beanList) {
			CreateJobPayload payload = CreateJobBeanMapper.mapper(bean);
			payloadList.add(payload);
		}
		
		for(CreateJobPayload payload: payloadList) {
			System.out.println(payload);
		}
		
	}

}
