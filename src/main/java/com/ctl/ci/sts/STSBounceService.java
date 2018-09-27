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
import com.ctl.ci.components.BounceInput;
import com.ctl.ci.components.BounceOutput;
import com.ctl.ci.components.STSOutput;
import com.ctl.ci.stapler.recorder.ContinuousDeliveryRecorder.ContinuousDeliveryOptions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author AB40286
 *
 */
public final class STSBounceService extends AbstractSTSService implements STSService {

	/**
	 * @param requestType
	 */
	protected STSBounceService(final STS_TYPE type) {
		super(type);
	}

	@Override
	public STSOutput processResponse(final String response)
			throws JsonParseException, JsonMappingException, IOException {
		STSOutput bounceOutput = null;
		if (StringUtils.isNotBlank(response)) {
			bounceOutput = JSONUtils.getJavaObject(response.toString(), BounceOutput.class);
		}
		return bounceOutput;
	}

	@Override
	public BounceInput buildRequestBean(final ContinuousDeliveryOptions continuousDeliveryOptions,
			final BuildUser buildUser, final Map<String, String> buildMap) {
		return getBounceBean(continuousDeliveryOptions, buildUser, buildMap);
	}

	/**
	 * @param continuousDeliveryOptions
	 * @param buildUser
	 * @param buildMap
	 * @return BounceInput
	 */
	private BounceInput getBounceBean(final ContinuousDeliveryOptions continuousDeliveryOptions,
			final BuildUser buildUser, final Map<String, String> buildMap) {
		final BounceInput bounceInput = new BounceInput();
		bounceInput.setApplicationMAL(buildMap.get(STS_APPLICATION));
		bounceInput.setEnvironment(buildMap.get(STS_ENVIRONMENT));
		bounceInput.setRequestedDateTimeUTC(DateUtils.getDateByTimeZoneAndFormat(TIME_ZONE, SIMPLE_DATE_FORMAT));
		bounceInput.setRequestorId(buildUser.getBuildUserId());
		bounceInput.setSpecialRequest(SPECIAL_REQUESTS);
		bounceInput.setProject(continuousDeliveryOptions.getProjects());
		bounceInput.setDevPOC(buildUser.getBuildUserLastName() + ", " + buildUser.getBuildUserFirstName());
		bounceInput.setBounceType(BOUNCE_TYPE);
		bounceInput.setReleaseTarget(DateUtils.getYearByMonth(buildMap.get(STS_MONTH)));
		bounceInput.setBounceReason(BOUNCE_REASON);
		bounceInput.setClient(CLIENT_TYPE);
		bounceInput.setMailingList(buildMap.get(STS_MAILING_LIST));
		return bounceInput;
	}
}
