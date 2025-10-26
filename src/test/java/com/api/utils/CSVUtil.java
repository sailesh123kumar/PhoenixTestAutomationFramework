package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {
	
	public static void main(String[] args) throws IOException, CsvException {
		
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData\\logInTestData.csv");
		InputStreamReader reader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(reader);
		List<String[]> lines = csvReader.readAll();
		
		for(String[] line:lines) {
			for(String data :line) {
				System.out.print(data + " "); 
			}
			System.out.println(""); 
		}
		
		csvReader.close();
		
	}

}
