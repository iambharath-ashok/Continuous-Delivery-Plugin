package com.ctl.ci.cause.determinant.impl;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.cause.determinant.ICauseDeterminant;
import com.ctl.ci.cause.utils.UserInfoUtils;

import hudson.triggers.SCMTrigger;
import hudson.triggers.SCMTrigger.SCMTriggerCause;

/**
 * @author AB40286
 *
 */
public final class SCMTriggerCauseDeterminant implements ICauseDeterminant<SCMTrigger.SCMTriggerCause> {

	public static SCMTriggerCauseDeterminant INSTANCE = getInstance();

	private SCMTriggerCauseDeterminant() {
	}

	private static SCMTriggerCauseDeterminant getInstance() {
		if (INSTANCE == null) {
			synchronized (SCMTriggerCauseDeterminant.class) {
				if (INSTANCE == null) {
					return INSTANCE = new SCMTriggerCauseDeterminant();
				}
			}
		}
		return INSTANCE;
	}

	private final Class<SCMTrigger.SCMTriggerCause> causeClass = SCMTrigger.SCMTriggerCause.class;

	public boolean setJenkinsUserBuildVars(final SCMTriggerCause cause, final BuildUser buildUser) {
		if (cause != null) {
			UserInfoUtils.setUserNameDeatils("SCMTrigger", buildUser);
			return true;
		} else {
			return false;
		}
	}

	public Class<SCMTriggerCause> getUsedCauseClass() {
		return causeClass;
	}
}
