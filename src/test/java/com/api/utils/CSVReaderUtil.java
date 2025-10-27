package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	private CSVReaderUtil() {
		
	}
	
	public static Iterator<UserBean> loadCSV(String pathOfCSV)  {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSV);
		InputStreamReader inpuStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inpuStreamReader);
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
												.withType(UserBean.class)
												.withIgnoreEmptyLine(true)
												.build();
		
		List<UserBean> userList = csvToBean.parse();
		try {
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return userList.iterator();
		
	}

}
