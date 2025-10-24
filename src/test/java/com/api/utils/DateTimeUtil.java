package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	private DateTimeUtil() {
		
	}
	
	public static String getTimeWithDaysAgo(int days) {
		String date = Instant.now().minus(days, ChronoUnit.DAYS).toString();
		return date;
	}

}
