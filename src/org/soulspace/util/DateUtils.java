/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
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