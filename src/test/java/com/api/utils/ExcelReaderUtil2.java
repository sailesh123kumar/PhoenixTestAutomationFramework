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
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {

	public static <T> Iterator<T> loadTestData(String fileName , String sheetName , Class<T> clazz) {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(fileName);

		XSSFWorkbook workBook = null;
		
		try {
			workBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet;
		sheet = workBook.getSheet(sheetName);
		
		List<T> list = Poiji.fromExcel(sheet, clazz);
		return list.iterator();
	}

}
