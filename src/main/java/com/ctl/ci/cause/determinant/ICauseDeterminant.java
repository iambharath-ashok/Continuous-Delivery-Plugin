package com.ctl.ci.cause.determinant;

import hudson.model.Cause;

/**
 * @author AB40286
 *
 * @param <T>
 */
public interface ICauseDeterminant<T extends Cause> {

	/**
	 * @param cause
	 * @param buildUser
	 * @return boolean
	 */
	boolean setJenkinsUserBuildVars(final T cause, final BuildUser buildUser);

	/**
	 * @return Class
	 */
	Class<T> getUsedCauseClass();

}
