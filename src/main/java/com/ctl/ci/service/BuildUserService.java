/**
 * 
 */
package com.ctl.ci.service;

import javax.annotation.Nonnull;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.cause.determinant.impl.SCMTriggerCauseDeterminant;
import com.ctl.ci.cause.determinant.impl.UserCauseDeterminant;
import com.ctl.ci.cause.determinant.impl.UserIdCauseDeterminant;

import hudson.model.AbstractBuild;
import hudson.model.Cause;
import hudson.model.Job;
import hudson.model.Run;
import hudson.model.Cause.UserCause;
import hudson.model.Cause.UserIdCause;
import hudson.triggers.SCMTrigger;
import jenkins.model.Jenkins;

/**
 * @author AB40286
 *
 */
public final class BuildUserService {

	/**
	 * @param build
	 * @param variables
	 */
	private static BuildUser makeUserBuildVariables(final @Nonnull Run<?, ?> build,final  @Nonnull BuildUser buildUser) {

		final Cause.UpstreamCause upstreamCause = (Cause.UpstreamCause) build.getCause(Cause.UpstreamCause.class);
		if (upstreamCause != null) {
			final Job job = Jenkins.getInstance().getItemByFullName(upstreamCause.getUpstreamProject(), Job.class);
			if (job != null) {
				final Run upstream = job.getBuildByNumber(upstreamCause.getUpstreamBuild());
				if (upstream != null) {
					makeUserBuildVariables(upstream, buildUser);
				}
			}
		}

		final SCMTrigger.SCMTriggerCause scmTriggerCause = (SCMTrigger.SCMTriggerCause) build
				.getCause(SCMTriggerCauseDeterminant.INSTANCE.getUsedCauseClass());
		if (SCMTriggerCauseDeterminant.INSTANCE.setJenkinsUserBuildVars(scmTriggerCause, buildUser)) {
			return buildUser;
		}

		final UserIdCause userIdCause = (UserIdCause) build.getCause(UserIdCauseDeterminant.INSTANCE.getUsedCauseClass());
		if (UserIdCauseDeterminant.INSTANCE.setJenkinsUserBuildVars(userIdCause, buildUser)) {
			return buildUser;
		}

		final UserCause userCause = (UserCause) build.getCause(UserCauseDeterminant.INSTANCE.getUsedCauseClass());
		if (UserCauseDeterminant.INSTANCE.setJenkinsUserBuildVars(userCause, buildUser)) {
			return buildUser;
		}
		return buildUser;
	}

	/**
	 * @param build
	 * @param launcher
	 * @param listener
	 * @return Map<String, String>
	 */
	public static BuildUser getBuildUser(AbstractBuild<?, ?> build) {
		return makeUserBuildVariables(build, new BuildUser());
	}
}
