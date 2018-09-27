package com.ctl.ci.components;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author AB40286
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class InstallInput implements STSInput {

	private String applicationMAL;
	private String environment;
	private String dimDb;
	private String dimProject;
	private String dimWorkset;
	private String dimSmr;
	private String dimCodeVersion;
	private String devPOC;
	private String project;
	private String releaseTarget;
	private String requestedDateTimeUTC;
	private String requestorId;
	private String specialRequest;
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

	public String getDimDb() {
		return dimDb;
	}

	public void setDimDb(String dimDb) {
		this.dimDb = dimDb;
	}

	public String getDimProject() {
		return dimProject;
	}

	public void setDimProject(String dimProject) {
		this.dimProject = dimProject;
	}

	public String getDimWorkset() {
		return dimWorkset;
	}

	public void setDimWorkset(String dimWorkset) {
		this.dimWorkset = dimWorkset;
	}

	public String getDimSmr() {
		return dimSmr;
	}

	public void setDimSmr(String dimSmr) {
		this.dimSmr = dimSmr;
	}

	public String getDimCodeVersion() {
		return dimCodeVersion;
	}

	public void setDimCodeVersion(String dimCodeVersion) {
		this.dimCodeVersion = dimCodeVersion;
	}

	public String getRequestedDateTimeUTC() {
		return requestedDateTimeUTC;
	}

	public void setRequestedDateTimeUTC(String requestedTime) {
		this.requestedDateTimeUTC = requestedTime;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
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

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getReleaseTarget() {
		return releaseTarget;
	}

	public void setReleaseTarget(String releaseTarget) {
		this.releaseTarget = releaseTarget;
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
		return "InstallInput [applicationMAL=" + applicationMAL + ", environment=" + environment + ", dimDb=" + dimDb
				+ ", dimProject=" + dimProject + ", dimWorkset=" + dimWorkset + ", dimSmr=" + dimSmr
				+ ", dimCodeVersion=" + dimCodeVersion + ", devPOC=" + devPOC + ", project=" + project
				+ ", releaseTarget=" + releaseTarget + ", requestedDateTimeUTC=" + requestedDateTimeUTC
				+ ", requestorId=" + requestorId + ", specialRequest=" + specialRequest + ", client=" + client
				+ ", mailingList=" + mailingList + "]";
	}

}
