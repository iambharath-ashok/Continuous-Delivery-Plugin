package com.ctl.ci.cause.determinant.impl;

import org.apache.commons.lang.StringUtils;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.cause.determinant.ICauseDeterminant;
import com.ctl.ci.cause.utils.UserInfoUtils;

import hudson.model.User;
import hudson.model.UserProperty;
import hudson.model.Cause.UserIdCause;

/**
 * @author AB40286
 *
 */
public final class UserIdCauseDeterminant implements ICauseDeterminant<UserIdCause> {

	private final Class<UserIdCause> causeClass = UserIdCause.class;

	public static UserIdCauseDeterminant INSTANCE = getInstance();

	private static UserIdCauseDeterminant getInstance() {
		if (INSTANCE == null) {
			synchronized (UserIdCauseDeterminant.class) {
				if (INSTANCE == null) {
					return new UserIdCauseDeterminant();
				}
			}
		}
		return INSTANCE;
	}

	private UserIdCauseDeterminant() {
	}

	public boolean setJenkinsUserBuildVars(final UserIdCause cause, final BuildUser buildUser) {
		if (cause != null) {
			String userName = cause.getUserName();
			UserInfoUtils.setUserNameDeatils(userName, buildUser);
			String userId = StringUtils.trimToEmpty(cause.getUserId());
			buildUser.setBuildUserId(userId);

			User user = User.get(userId);
			if (user != null) {
				UserProperty prop = user.getProperty(hudson.tasks.Mailer.UserProperty.class);
				if (prop != null) {
					String eMailAdrs = StringUtils.trimToEmpty(((hudson.tasks.Mailer.UserProperty) prop).getAddress());
					buildUser.setBuildUserEmail(eMailAdrs);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public Class<UserIdCause> getUsedCauseClass() {
		return causeClass;
	}

}
