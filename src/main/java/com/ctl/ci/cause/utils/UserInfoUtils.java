package com.ctl.ci.cause.utils;

import org.apache.commons.lang.StringUtils;

import com.ctl.ci.cause.determinant.BuildUser;

/**
 * @author AB40286
 *
 */
public final class UserInfoUtils {

	/**
	 * @param userName
	 * @param buildUser
	 */
	public static void setUserNameDeatils(final String userName, final BuildUser buildUser) {
		buildUser.setBuildUserFullName(userName);
		buildUser.setBuildUserFirstName(getFirstName(userName));
		buildUser.setBuildUserLastName(getLastName(userName));
	}

	/**
	 * @param fullName
	 * @return firstName
	 */
	public static String getFirstName(final String fullName) {
		final String[] parts = StringUtils.trimToEmpty(fullName).split("\\s+");
		return parts.length > 0 ? parts[0] : "";
	}

	/**
	 * @param fullName
	 * @return lastName
	 */
	public static String getLastName(final String fullName) {
		final String[] parts = StringUtils.trimToEmpty(fullName).split("\\s+");
		return parts.length >= 2 ? parts[1] : "";
	}
}
