package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ReadCSV_MapToPojo {
	
	
	public static void main(String[] args) throws IOException {
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData\\logInTestData.csv");
		InputStreamReader inpuStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inpuStreamReader);
		
		CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader)
												.withType(UserPOJO.class)
												.withIgnoreEmptyLine(true)
												.build();
		
		
		List<UserPOJO> userList = csvToBean.parse();
		System.out.println(userList);
		System.out.println(userList.get(0).getUsername());
		System.out.println(userList.get(0).getPassword());
		System.out.println(userList.get(1).getUsername());
		System.out.println(userList.get(1).getPassword());
		csvReader.close();
		
	}

}
