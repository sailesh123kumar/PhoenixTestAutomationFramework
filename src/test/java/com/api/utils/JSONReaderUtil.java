package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReaderUtil {

	public static <T> Iterator<T> loadJSON(String fileName , Class<T[]> clazz) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] userCreadentialsArray = null;
		List<T> list = null;
		try {
			userCreadentialsArray = objectMapper.readValue(is, clazz);
			list = Arrays.asList(userCreadentialsArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list.iterator();

	}

}
