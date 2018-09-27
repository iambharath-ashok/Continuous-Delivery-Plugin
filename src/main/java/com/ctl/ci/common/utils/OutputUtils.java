/**
 * 
 */
package com.ctl.ci.common.utils;

import java.io.IOException;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.components.BounceOutput;
import com.ctl.ci.components.InstallOutput;
import com.ctl.ci.components.STSOutput;
import com.ctl.ci.properties.IProperties;
import com.ctl.ci.stapler.recorder.ContinuousDeliveryRecorder.ContinuousDeliveryOptions;

import hudson.model.BuildListener;

/**
 * @author AB40286
 *
 */
public final class OutputUtils {
	/**
	 * @param output
	 * @param listener
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean processSTSOutput(final STSOutput output, final BuildListener listener) throws IOException {
		if (output != null) {
			if (output instanceof BounceOutput) {
				listener.getLogger().println("");
				listener.getLogger().println("Bounce Response: " + JSONUtils.getJsonString(output));
				if ("Success".equalsIgnoreCase(((BounceOutput) output).getStatus())) {
					listener.getLogger().println("");
					listener.getLogger().println("Bounce Request is Successful");
					listener.getLogger().println("ApplicReqId: " + ((BounceOutput) output).getApplicReqId());
					listener.getLogger()
							.println("ApplicReqStatusId: " + ((BounceOutput) output).getApplicReqStatusId());
					listener.getLogger().println("ResultMsg: " + ((BounceOutput) output).getResultMsg());
					listener.hyperlink(IProperties.STS_REQUEST_STATUS_LINK + ((BounceOutput) output).getApplicReqId(),
							IProperties.STS_BOUNCE_REQUEST_LINK_MESSAGE + IProperties.STS_REQUEST_STATUS_LINK
									+ ((BounceOutput) output).getApplicReqId());
					listener.getLogger().println("");
					return true;
				} else if ("Failed".equalsIgnoreCase(((BounceOutput) output).getStatus())) {
					listener.getLogger().println("");
					listener.getLogger().println("Bounce Request has Failed");
					listener.getLogger().println("ResultMsg: " + ((BounceOutput) output).getResultMsg());
					listener.getLogger().println("");
					return false;
				} else {
					return false;
				}
			} else if (output instanceof InstallOutput) {
				listener.getLogger().println("");
				listener.getLogger().println("Install Response: " + JSONUtils.getJsonString(output));
				if ("Success".equalsIgnoreCase(((InstallOutput) output).getStatus())) {
					listener.getLogger().println("");
					listener.getLogger().println("Install Request is Successful");
					listener.getLogger().println("ApplicReqId: " + ((InstallOutput) output).getApplicReqId());
					listener.getLogger()
							.println("ApplicReqStatusId: " + ((InstallOutput) output).getApplicReqStatusId());
					listener.getLogger().println("ResultMsg: " + ((InstallOutput) output).getResultMsg());
					listener.hyperlink(IProperties.STS_REQUEST_STATUS_LINK + ((InstallOutput) output).getApplicReqId(),
							IProperties.STS_INSTALL_REQUEST_LINK_MESSAGE + IProperties.STS_REQUEST_STATUS_LINK
									+ ((InstallOutput) output).getApplicReqId());
					listener.getLogger().println("");
					return true;
				} else if ("Failed".equalsIgnoreCase(((InstallOutput) output).getStatus())) {
					listener.getLogger().println("");
					listener.getLogger().println("Install Request has Failed");
					listener.getLogger().println("ResultMsg: " + ((InstallOutput) output).getResultMsg());
					listener.getLogger().println("");
					return false;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * @param continuousDeliveryOptionsBean
	 * @param listener
	 * @param buildUser
	 * @return
	 */
	public static boolean processCodeFreezeOutput(final ContinuousDeliveryOptions continuousDeliveryOptionsBean,
			final BuildListener listener, final BuildUser buildUser) {
		listener.getLogger().println("");
		listener.getLogger().println("Install Request Has Failed.");
		listener.getLogger().println("");
		listener.getLogger().println("Application : " + continuousDeliveryOptionsBean.getApplications());
		listener.getLogger().println("Environment :" + continuousDeliveryOptionsBean.getEnvironments());
		listener.getLogger().println("Dimensions Version : " + continuousDeliveryOptionsBean.getDimensionsVersion());
		listener.getLogger().println("SMR : " + continuousDeliveryOptionsBean.getDimensionSMRs());
		listener.getLogger().println("Requestor : " + buildUser.getBuildUserFullName());
		listener.getLogger().println("Requestor Id : " + buildUser.getBuildUserId());
		listener.getLogger().println("Requestor Email : " + buildUser.getBuildUserEmail());
		listener.getLogger().println("Month : " + continuousDeliveryOptionsBean.getReleaseMonth());
		listener.getLogger().println("");
		listener.getLogger()
				.println("Code Freeze Is In Progress From " + continuousDeliveryOptionsBean.getCodeFreezeStartDate()
						+ " to " + continuousDeliveryOptionsBean.getCodeFreezeEndDate() + " For Application "
						+ continuousDeliveryOptionsBean.getApplications() + " In Environment "
						+ continuousDeliveryOptionsBean.getEnvironments() + "!");
		listener.getLogger().println("");
		return false;
	}
}
