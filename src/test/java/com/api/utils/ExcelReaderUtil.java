package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {
	
	public static void main(String[] args) throws IOException {
		
		InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");
		
		XSSFWorkbook workBook = new XSSFWorkbook(is);
		XSSFSheet sheet = workBook.getSheet("LoginTestData");
		XSSFRow myRow;
		XSSFCell myCell;
		
		int lastRowNum = sheet.getLastRowNum();
		int lastColumnNum = sheet.getRow(0).getLastCellNum();
		
		System.out.println(lastRowNum);
		System.out.println(lastColumnNum);
		
		for(int row = 0 ; row <= lastRowNum ; row++) {
			for(int col = 0 ; col <= lastColumnNum - 1 ; col++) {
				 myRow = sheet.getRow(row);
				 myCell = myRow.getCell(col);
				 System.out.print(myCell.getStringCellValue() + " | ");
			}
			System.out.println("");
		}
		
	}

}
