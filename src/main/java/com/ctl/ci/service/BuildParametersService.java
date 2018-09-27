/**
 * 
 */
package com.ctl.ci.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ctl.ci.properties.IProperties;
import com.ctl.ci.stapler.recorder.ContinuousDeliveryRecorder.ContinuousDeliveryOptions;

import hudson.model.AbstractBuild;

/**
 * @author AB40286
 *
 */
public final class BuildParametersService {

	/**
	 * @param continuousDeliveryOptionsBean
	 * @param build
	 * @param build
	 * @return Map<String, String>
	 */
	public static Map<String, String> getBuildDataMap(final ContinuousDeliveryOptions continuousDeliveryOptionsBean,
			final AbstractBuild<?, ?> build) {
		final Map<String, String> buildMap = new HashMap<String, String>();
		if (build.getBuildVariables() != null && !build.getBuildVariables().isEmpty()) {
			build.getBuildVariables().forEach((k, v) -> {
				if(k.equalsIgnoreCase(IProperties.STS_MAILING_LIST) || k.equalsIgnoreCase(IProperties.STS_MONTH) || 
						k.equalsIgnoreCase(IProperties.STS_APPLICATION) || k.equalsIgnoreCase(IProperties.STS_ENVIRONMENT))
					buildMap.put(k, v);
			});
			if(buildMap.isEmpty()) prepareBuildMapFromContinuousDeliveryOptionsBean(continuousDeliveryOptionsBean, buildMap);
		}
		 else prepareBuildMapFromContinuousDeliveryOptionsBean(continuousDeliveryOptionsBean, buildMap);
		prepareMailingList(continuousDeliveryOptionsBean, build, buildMap);
		prepareDimensionsCodeVersion(continuousDeliveryOptionsBean, build, buildMap);
		return buildMap;
	}

	/**
	 * @param continuousDeliveryOptionsBean
	 * @param buildMap 
	 */
	private static void prepareBuildMapFromContinuousDeliveryOptionsBean(
			final ContinuousDeliveryOptions continuousDeliveryOptionsBean, final Map<String, String> buildMap) {
		buildMap.put(IProperties.STS_APPLICATION, continuousDeliveryOptionsBean.getApplications());
		buildMap.put(IProperties.REQUEST_TYPE, continuousDeliveryOptionsBean.getRequestType());
		buildMap.put(IProperties.STS_ENVIRONMENT, continuousDeliveryOptionsBean.getEnvironments());
		buildMap.put(IProperties.STS_MONTH, continuousDeliveryOptionsBean.getReleaseMonth());
		buildMap.put(IProperties.STS_MAILING_LIST, continuousDeliveryOptionsBean.getMailingList());
	}

	/**
	 * @param continuousDeliveryOptionsBean
	 * @param build
	 * @param buildMap
	 * @return Map<String, String>
	 */
	private static Map<String, String> prepareDimensionsCodeVersion(
			final ContinuousDeliveryOptions continuousDeliveryOptionsBean, final AbstractBuild<?, ?> build,
			final Map<String, String> buildMap) {
		if (StringUtils.isNotBlank(continuousDeliveryOptionsBean.getDimensionsVersion())) {
			final String dimensionsCodeBaseVersion = StringUtils
					.stripToEmpty(continuousDeliveryOptionsBean.getDimensionsVersion());
			buildMap.put(IProperties.DIMENSION_CODE_VERSION, dimensionsCodeBaseVersion.trim() + "." + build.getNumber());
		}
		return buildMap;
	}

	/**
	 * @param continuousDeliveryOptionsBean
	 * @param build
	 * @param buildMap
	 */
	private static Map<String, String> prepareMailingList(final ContinuousDeliveryOptions continuousDeliveryOptionsBean,
			final AbstractBuild<?, ?> build, final Map<String, String> buildMap) {
		if (!StringUtils.isNotBlank(buildMap.get(IProperties.STS_MAILING_LIST)) && StringUtils.isNotBlank(continuousDeliveryOptionsBean.getMailingList())) {
			final String mailingList = StringUtils.stripToEmpty(continuousDeliveryOptionsBean.getMailingList());
			buildMap.put(IProperties.STS_MAILING_LIST, mailingList.trim());
		} 
		return buildMap;
	}
}
