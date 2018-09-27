/**
 * 
 */
package com.ctl.ci.components;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author AB40286
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class InstallOutput implements STSOutput {

	private String applicReqId;
	private String applicReqStatusId;
	private String inputApplicationMAL;
	private String inputDevPOC;
	private String inputDimCodeVersion;
	private String inputDimDb;
	private String inputDimProject;
	private String inputDimSmr;
	private String inputDimWorkset;
	private String inputEnvironment;
	private String inputProject;
	private String inputReleaseTarget;
	private String inputRequestedDateTimeUTC;
	private String inputRequestorId;
	private String inputSpecialRequest;
	private String resultMsg;
	private String status;
	private String svcReqId;

	public String getApplicReqId() {
		return applicReqId;
	}

	public void setApplicReqId(String applicReqId) {
		this.applicReqId = applicReqId;
	}

	public String getApplicReqStatusId() {
		return applicReqStatusId;
	}

	public void setApplicReqStatusId(String applicReqStatusId) {
		this.applicReqStatusId = applicReqStatusId;
	}

	public String getInputApplicationMAL() {
		return inputApplicationMAL;
	}

	public void setInputApplicationMAL(String inputApplicationMAL) {
		this.inputApplicationMAL = inputApplicationMAL;
	}

	public String getInputDevPOC() {
		return inputDevPOC;
	}

	public void setInputDevPOC(String inputDevPOC) {
		this.inputDevPOC = inputDevPOC;
	}

	public String getInputDimCodeVersion() {
		return inputDimCodeVersion;
	}

	public void setInputDimCodeVersion(String inputDimCodeVersion) {
		this.inputDimCodeVersion = inputDimCodeVersion;
	}

	public String getInputDimDb() {
		return inputDimDb;
	}

	public void setInputDimDb(String inputDimDb) {
		this.inputDimDb = inputDimDb;
	}

	public String getInputDimProject() {
		return inputDimProject;
	}

	public void setInputDimProject(String inputDimProject) {
		this.inputDimProject = inputDimProject;
	}

	public String getInputDimSmr() {
		return inputDimSmr;
	}

	public void setInputDimSmr(String inputDimSmr) {
		this.inputDimSmr = inputDimSmr;
	}

	public String getInputDimWorkset() {
		return inputDimWorkset;
	}

	public void setInputDimWorkset(String inputDimWorkset) {
		this.inputDimWorkset = inputDimWorkset;
	}

	public String getInputEnvironment() {
		return inputEnvironment;
	}

	public void setInputEnvironment(String inputEnvironment) {
		this.inputEnvironment = inputEnvironment;
	}

	public String getInputProject() {
		return inputProject;
	}

	public void setInputProject(String inputProject) {
		this.inputProject = inputProject;
	}

	public String getInputReleaseTarget() {
		return inputReleaseTarget;
	}

	public void setInputReleaseTarget(String inputReleaseTarget) {
		this.inputReleaseTarget = inputReleaseTarget;
	}

	public String getInputRequestedDateTimeUTC() {
		return inputRequestedDateTimeUTC;
	}

	public void setInputRequestedDateTimeUTC(String inputRequestedDateTimeUTC) {
		this.inputRequestedDateTimeUTC = inputRequestedDateTimeUTC;
	}

	public String getInputRequestorId() {
		return inputRequestorId;
	}

	public void setInputRequestorId(String inputRequestorId) {
		this.inputRequestorId = inputRequestorId;
	}

	public String getInputSpecialRequest() {
		return inputSpecialRequest;
	}

	public void setInputSpecialRequest(String inputSpecialRequest) {
		this.inputSpecialRequest = inputSpecialRequest;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSvcReqId() {
		return svcReqId;
	}

	public void setSvcReqId(String svcReqId) {
		this.svcReqId = svcReqId;
	}

	@Override
	public String toString() {
		return "InstallOutput [applicReqId=" + applicReqId + ", applicReqStatusId=" + applicReqStatusId
				+ ", inputApplicationMAL=" + inputApplicationMAL + ", inputDevPOC=" + inputDevPOC
				+ ", inputDimCodeVersion=" + inputDimCodeVersion + ", inputDimDb=" + inputDimDb + ", inputDimProject="
				+ inputDimProject + ", inputDimSmr=" + inputDimSmr + ", inputDimWorkset=" + inputDimWorkset
				+ ", inputEnvironment=" + inputEnvironment + ", inputProject=" + inputProject + ", inputReleaseTarget="
				+ inputReleaseTarget + ", inputRequestedDateTimeUTC=" + inputRequestedDateTimeUTC
				+ ", inputRequestorId=" + inputRequestorId + ", inputSpecialRequest=" + inputSpecialRequest
				+ ", resultMsg=" + resultMsg + ", status=" + status + ", svcReqId=" + svcReqId + "]";
	}

}
