/**
 * 
 */
package com.ctl.ci.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.common.utils.JSONUtils;
import com.ctl.ci.common.utils.MiscUtils;
import com.ctl.ci.common.utils.OutputUtils;
import com.ctl.ci.components.STSInput;
import com.ctl.ci.components.STSOutput;
import com.ctl.ci.service.BuildParametersService;
import com.ctl.ci.stapler.recorder.ContinuousDeliveryRecorder.ContinuousDeliveryOptions;
import com.ctl.ci.sts.STSService;
import com.ctl.ci.sts.STSService.STS_TYPE;

import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;

/**
 * @author AB40286
 *
 */
public final class STSServiceController {

	/**
	 * @param build
	 * @param launcher
	 * @param listener
	 * @param continuousDeliveryOptionsBean
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws TemplateException
	 * @throws EMailConfigurationException
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	public static boolean callService(final AbstractBuild<?, ?> build, final Launcher launcher,
			final BuildListener listener, final ContinuousDeliveryOptions continuousDeliveryOptionsBean,
			final BuildUser buildUser)
			throws IOException, InterruptedException, AddressException, MessagingException, KeyManagementException,
			UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		STSService stsService;
		String stsResponse;
		STSOutput stsOutput;
		STSInput stsInput;
		final Map<String, String> buildMap = BuildParametersService.getBuildDataMap(continuousDeliveryOptionsBean,
				build);

		switch (continuousDeliveryOptionsBean.getRequestType()) {

		case "BOUNCE":
			stsService = STS_TYPE.BOUNCE.getInstance();
			stsInput = stsService.buildRequestBean(continuousDeliveryOptionsBean, buildUser, buildMap);
			listener.getLogger().println("");
			listener.getLogger().println("Bounce Request: " + JSONUtils.getJsonString(stsInput));
			stsResponse = stsService.getSTSResponse(stsService.callSTSService(stsInput));
			stsOutput = stsService.processResponse(stsResponse);
			return OutputUtils.processSTSOutput(stsOutput, listener);

		case "INSTALL":
			if (MiscUtils.isCodeFreezeInProgress(continuousDeliveryOptionsBean.getCodeFreezeStartDate(),
					continuousDeliveryOptionsBean.getCodeFreezeEndDate())) {
				return OutputUtils.processCodeFreezeOutput(continuousDeliveryOptionsBean, listener, buildUser);
			} else {
				stsService = STS_TYPE.INSTALL.getInstance();
				stsInput = stsService.buildRequestBean(continuousDeliveryOptionsBean, buildUser, buildMap);
				listener.getLogger().println("");
				listener.getLogger().println("Install Request: " + JSONUtils.getJsonString(stsInput));
				stsResponse = stsService.getSTSResponse(stsService.callSTSService(stsInput));
				stsOutput = stsService.processResponse(stsResponse);
				return OutputUtils.processSTSOutput(stsOutput, listener);
			}
		default:
			return false;
		}
	}

}
