/**
 * 
 */
package com.ctl.ci.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.ctl.ci.properties.IProperties;

/**
 * @author AB40286
 *
 */
public final class DateUtils {

	/**
	 * @param timezone
	 * @param format
	 * @return
	 */
	public static String getDateByTimeZoneAndFormat(final String timezone, final String format) {
		final SimpleDateFormat dateFormatGmt = new SimpleDateFormat(format);
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone(timezone));
		return dateFormatGmt.format(new Date());
	}

	/**
	 * @return
	 */
	public static Integer getYear() {
		return Year.now().getValue();
	}

	/**
	 * @return
	 */
	public static List<String> getFutureMonths() {
		final List<String> monthYearList = new ArrayList<>(12);
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		for (int i = 1; i <= Month.values().length; i++) {
			monthYearList.add(new SimpleDateFormat(IProperties.SIMPLE_DATE_FORMAT_MONTH).format(calendar.getTime()));
			calendar.add(Calendar.MONTH, +1);
		}
		return monthYearList;
	}

	/**
	 * @param releaseMonth
	 * @return
	 */
	public static String getYearByMonth(final String month) {
		if (Month.valueOf(month.toUpperCase()).getValue() < LocalDate.now().getMonthValue())
			return month + "_" + Integer.valueOf(LocalDate.now().getYear() + 1);
		else
			return month + "_" + getYear();
	}

	/**
	 * @param date
	 * @return boolean
	 */
	public static boolean isValidDate(final String date) {
		try {
			LocalDate.parse(date, getDateTimeFormatter(IProperties.CODE_FREEZE_DATE_FORMAT));
			/*if (isPreviousDate(date))
				return true;
			else
				return false;*/
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @return Todays Date in String
	 */
	public static String getTodaysDate() {
		DateTimeFormatter formatter = getDateTimeFormatter(IProperties.CODE_FREEZE_DATE_FORMAT);
		LocalDate dateNow = LocalDate.now();
		return formatter.format(dateNow);
	}

	/**
	 * @param date
	 * @return boolean
	 */
	public static boolean isFutureDate(final String date) {
		try {
			LocalDate validateDate = LocalDate.parse(date, getDateTimeFormatter(IProperties.CODE_FREEZE_DATE_FORMAT));
			return !validateDate.isAfter(LocalDate.now());
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * @param date
	 * @return boolean
	 */
	public static boolean isPreviousDate(final String date) {
		try {
			LocalDate validateDate = LocalDate.parse(date, getDateTimeFormatter(IProperties.CODE_FREEZE_DATE_FORMAT));
			return !validateDate.isBefore(LocalDate.now());
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * @param codeFreezeDateFormat 
	 * @return DateTimeFormatter
	 */
	private static DateTimeFormatter getDateTimeFormatter(String codeFreezeDateFormat) {
		return DateTimeFormatter.ofPattern(codeFreezeDateFormat);
	}
}
