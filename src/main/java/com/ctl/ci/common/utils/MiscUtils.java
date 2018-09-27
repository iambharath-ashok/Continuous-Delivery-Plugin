/**
 * 
 */
package com.ctl.ci.common.utils;

/**
 * @author AB40286
 *
 */
public class MiscUtils {

	/**
	 * @param startDate
	 * @param endDate
	 * @return boolean
	 */
	public static boolean isCodeFreezeInProgress(final String startDate, final String endDate) {
		return DateUtils.isFutureDate(startDate) && DateUtils.isPreviousDate(endDate);
	}
	
}
