package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

import io.qameta.allure.Step;

public class ExcelReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);
	
	private ExcelReaderUtil() {
	}

	@Step("Loading test data from the excel file")
	public static <T> Iterator<T> loadTestData(String fileName , String sheetName , Class<T> clazz) {
		
		LOGGER.info("Loading the Excel file from the path {} and the sheet name is {}",fileName ,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(fileName);

		XSSFWorkbook workBook = null;
		XSSFSheet sheet = null;
		try {
			workBook = new XSSFWorkbook(is);
			sheet = workBook.getSheet(sheetName);
		} catch (IOException e) {
			LOGGER.error("Something went Wrong, Cannot read the excel {}", fileName , e);
			e.printStackTrace();
		}
		
		LOGGER.info("Converting the XSSFSheet {} to POJO class of type {}",sheetName,clazz);
		List<T> list = Poiji.fromExcel(sheet, clazz);
		return list.iterator();
	}

}
