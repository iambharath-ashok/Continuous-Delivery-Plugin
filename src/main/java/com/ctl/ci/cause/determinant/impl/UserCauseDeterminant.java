package com.ctl.ci.cause.determinant.impl;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.cause.determinant.ICauseDeterminant;
import com.ctl.ci.cause.utils.UserInfoUtils;

import hudson.model.Cause.UserCause;

/**
 * @author AB40286
 *
 */
@SuppressWarnings("deprecation")
public final class UserCauseDeterminant implements ICauseDeterminant<UserCause> {
	public static UserCauseDeterminant INSTANCE = getInstance();

	private UserCauseDeterminant() {
	}

	private static UserCauseDeterminant getInstance() {
		if (INSTANCE == null) {
			synchronized (UserCauseDeterminant.class) {
				if (INSTANCE == null) {
					return INSTANCE = new UserCauseDeterminant();
				}
			}
		}
		return INSTANCE;
	}

	private final Class<UserCause> causeClass = UserCause.class;

	public boolean setJenkinsUserBuildVars(final UserCause cause, final BuildUser buildUser) {
		if (cause != null) {
			String username = cause.getUserName();
			UserInfoUtils.setUserNameDeatils(username, buildUser);
			return true;
		} else {
			return false;
		}
	}

	public Class<UserCause> getUsedCauseClass() {
		return causeClass;
	}

}
