package org.soulspace.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	/**
	 * 
	 * @param d0
	 * @param d1
	 * @return
	 */
	public static int compareTo(Date d0, Date d1) {
		int result = 0;
		if(d0 == null && d1 == null) {
			result = 0;
		} else if(d0 == null) {
			result = 1;
		} else if(d1 == null) {
			result = -1;
		} else {
			result = d0.compareTo(d1);
		}
		return result;
	}

	/**
	 * 
	 * @param d0
	 * @param d1
	 * @return
	 */
	// TODO delete if not used
	public static int compareFrom(Date d0, Date d1) {
		int result = 0;
		if(d0 == null && d1 == null) {
			result = 0;
		} else if(d0 == null) {
			result = -1;
		} else if(d1 == null) {
			result = 1;
		} else {
			result = d1.compareTo(d0);
		}
		return result;
	}
	
	// TODO don't use hardcoded constants here
	public static Date valueOf(String timeString) {
		try {
			return DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMANY).parse(timeString);			
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}