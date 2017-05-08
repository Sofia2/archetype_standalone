package com.indra.sofia2.archetype.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class CalendarUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static final Logger log = Logger.getLogger(CalendarUtil.class);
	
	public static String convert (Date date) {
		return format.format(date);
	}
	
	public static Date convert (String stringDate) {
		
		Date date = null;
		
		try {
			date = format.parse(stringDate);
		} catch (ParseException e) {
			log.error("Error converting date ", e);
		}
		
		return date;
	}
}
