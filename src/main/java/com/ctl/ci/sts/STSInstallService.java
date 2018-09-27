/**
 * 
 */
package com.ctl.ci.sts;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.common.utils.DateUtils;
import com.ctl.ci.common.utils.JSONUtils;
import com.ctl.ci.components.InstallInput;
import com.ctl.ci.components.InstallOutput;
import com.ctl.ci.components.STSOutput;
import com.ctl.ci.stapler.recorder.ContinuousDeliveryRecorder.ContinuousDeliveryOptions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author AB40286
 *
 */
public final class STSInstallService extends AbstractSTSService implements STSService {

	/**
	 * @param type
	 */
	protected STSInstallService(final STS_TYPE type) {
		super(type);
	}

	@Override
	public STSOutput processResponse(final String response)
			throws JsonParseException, JsonMappingException, IOException {
		STSOutput installOutput = null;
		if (StringUtils.isNotBlank(response)) {
			return installOutput = JSONUtils.getJavaObject(response.toString(), InstallOutput.class);
		}
		return installOutput;
	}

	@Override
	public InstallInput buildRequestBean(ContinuousDeliveryOptions continuousDeliveryOptionsBean, BuildUser buildUser,
			Map<String, String> buildMap) {
		return getInstallBean(continuousDeliveryOptionsBean, buildUser, buildMap);

	}

	/**
	 * @param continuousDeliveryOptions
	 * @param buildUser
	 * @param buildMap
	 * @return InstallInput
	 */
	private InstallInput getInstallBean(final ContinuousDeliveryOptions continuousDeliveryOptions, BuildUser buildUser,
			Map<String, String> buildMap) {
		final InstallInput installInput = new InstallInput();
		installInput.setApplicationMAL(continuousDeliveryOptions.getApplications());
		installInput.setEnvironment(continuousDeliveryOptions.getEnvironments());
		installInput.setDimDb(continuousDeliveryOptions.getDimensionDatabases());
		installInput.setDimProject(continuousDeliveryOptions.getDimensionProducts());
		installInput.setDimWorkset(continuousDeliveryOptions.getDimensionWorksets());
		installInput.setDimSmr(continuousDeliveryOptions.getDimensionSMRs());
		installInput.setDimCodeVersion(buildMap.get(DIMENSION_CODE_VERSION));
		installInput.setDevPOC(buildUser.getBuildUserLastName() + ", " + buildUser.getBuildUserFirstName());
		installInput.setProject(continuousDeliveryOptions.getProjects());
		installInput.setReleaseTarget(DateUtils.getYearByMonth(continuousDeliveryOptions.getReleaseMonth()));
		installInput.setRequestedDateTimeUTC(DateUtils.getDateByTimeZoneAndFormat(TIME_ZONE, SIMPLE_DATE_FORMAT));
		installInput.setRequestorId(buildUser.getBuildUserId());
		installInput.setSpecialRequest(SPECIAL_REQUESTS);
		installInput.setClient(CLIENT_TYPE);
		installInput.setMailingList(buildMap.get(STS_MAILING_LIST));
		return installInput;
	}

}
