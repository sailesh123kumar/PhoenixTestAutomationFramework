package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtil.class);
	
	private CSVReaderUtil() {
	}
	
	public static <T> Iterator<T> loadCSV(String pathOfCSV , Class<T> bean)  {
		LOGGER.info("Loading the CSV file from the path {}",pathOfCSV);
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSV);
		InputStreamReader inpuStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inpuStreamReader);
		
		LOGGER.info("Converting the CSV to the bean class {}",bean);
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
												.withType(bean)
												.withIgnoreEmptyLine(true)
												.build();
		
		List<T> userList = csvToBean.parse();
		return userList.iterator();
		
	}

}
