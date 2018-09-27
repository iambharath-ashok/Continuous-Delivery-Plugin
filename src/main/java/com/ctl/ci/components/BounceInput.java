package com.ctl.ci.components;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author AB40286
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BounceInput implements STSInput {

	private String applicationMAL;
	private String environment;
	private String bounceType;
	private String devPOC;
	private String project;
	private String releaseTarget;
	private String requestedDateTimeUTC;
	private String requestorId;
	private String specialRequest;
	private String bounceReason;
	private String client;
	private String mailingList;

	public String getApplicationMAL() {
		return applicationMAL;
	}

	public void setApplicationMAL(String applicationMAL) {
		this.applicationMAL = applicationMAL;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getBounceType() {
		return bounceType;
	}

	public void setBounceType(String bounceType) {
		this.bounceType = bounceType;
	}

	public String getDevPOC() {
		return devPOC;
	}

	public void setDevPOC(String devPOC) {
		this.devPOC = devPOC;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getReleaseTarget() {
		return releaseTarget;
	}

	public void setReleaseTarget(String releaseTarget) {
		this.releaseTarget = releaseTarget;
	}

	public String getRequestedDateTimeUTC() {
		return requestedDateTimeUTC;
	}

	public void setRequestedDateTimeUTC(String requestedDateTimeUTC) {
		this.requestedDateTimeUTC = requestedDateTimeUTC;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public String getBounceReason() {
		return bounceReason;
	}

	public void setBounceReason(String bounceReason) {
		this.bounceReason = bounceReason;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getMailingList() {
		return mailingList;
	}

	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}

	@Override
	public String toString() {
		return "BounceInput [applicationMAL=" + applicationMAL + ", environment=" + environment + ", bounceType="
				+ bounceType + ", devPOC=" + devPOC + ", project=" + project + ", releaseTarget=" + releaseTarget
				+ ", requestedDateTimeUTC=" + requestedDateTimeUTC + ", requestorId=" + requestorId
				+ ", specialRequest=" + specialRequest + ", bounceReason=" + bounceReason + ", client=" + client
				+ ", mailingList=" + mailingList + "]";
	}

}
