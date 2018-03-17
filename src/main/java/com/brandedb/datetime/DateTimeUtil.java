package com.brandedb.datetime;

import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;

/**
 * Util class for lightweight date and time manipulation using the joda time
 * library *
 */
public class DateTimeUtil {

	public static Instant toInstant(String dateString) {
		return new Instant(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(dateString));
	}

	public static Instant toInstant(long timestamp) {
		return new Instant(timestamp);
	}

	public static boolean isBetween(Instant startDate, Instant endDate, Instant compareDate) {
		return compareDate.isBefore(endDate) && compareDate.isAfter(startDate);
	}
}
