package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtil2 {

	public static Iterator<UserCredentials> loadTestData() {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");

		XSSFWorkbook workBook = null;
		XSSFSheet sheet;
		try {
			workBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = workBook.getSheet("LoginTestData");
		int lastRowNum = sheet.getLastRowNum();
		int lastColumnNum = sheet.getRow(0).getLastCellNum() - 1;

		XSSFRow headerRows = sheet.getRow(0);
		int UserNameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRows) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				UserNameIndex = cell.getColumnIndex();
			}
			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}
		}

		XSSFRow myRow;
		UserCredentials usercredentials;
		List<UserCredentials> userList = new ArrayList<UserCredentials>();

		for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
			myRow = sheet.getRow(rowIndex);
			usercredentials = new UserCredentials(myRow.getCell(UserNameIndex).getStringCellValue(),
					myRow.getCell(passwordIndex).getStringCellValue());
			userList.add(usercredentials);
		}

		return userList.iterator();
	}

}
