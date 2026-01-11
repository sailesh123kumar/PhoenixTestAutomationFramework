package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(JSONReaderUtil.class);
	
	private JSONReaderUtil() {
	}

	public static <T> Iterator<T> loadJSON(String fileName , Class<T[]> clazz) {
		LOGGER.info("Reading the JSon file from the path {} ",fileName);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] userCreadentialsArray = null;
		List<T> list = null;
		try {
			LOGGER.info("Converting the JSON Data to the bean class {}",clazz);
			userCreadentialsArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(userCreadentialsArray);
		} catch (IOException e) {
			LOGGER.error("Cannot read the json from the file {}",fileName,e);
			e.printStackTrace();
		}

		return list.iterator();

	}

}
