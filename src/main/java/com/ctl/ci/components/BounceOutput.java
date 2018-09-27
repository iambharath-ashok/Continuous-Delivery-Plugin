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
public class BounceOutput implements STSOutput {

	private String inputApplicationMAL;
	private String inputEnvironment;
	private String inputBounceType;
	private String inputDevPOC;
	private String inputProject;
	private String inputReleaseTarget;
	private String inputRequestedDateTimeUTC;
	private String inputRequestorId;
	private String inputSpecialRequest;
	private String inputBounceReason;
	private String resultMsg;
	private String status;
	private String svcReqId;
	private String applicReqId;
	private String applicReqStatusId;

	public String getInputApplicationMAL() {
		return inputApplicationMAL;
	}

	public void setInputApplicationMAL(String inputApplicationMAL) {
		this.inputApplicationMAL = inputApplicationMAL;
	}

	public String getInputEnvironment() {
		return inputEnvironment;
	}

	public void setInputEnvironment(String inputEnvironment) {
		this.inputEnvironment = inputEnvironment;
	}

	public String getInputBounceType() {
		return inputBounceType;
	}

	public void setInputBounceType(String inputBounceType) {
		this.inputBounceType = inputBounceType;
	}

	public String getInputDevPOC() {
		return inputDevPOC;
	}

	public void setInputDevPOC(String inputDevPOC) {
		this.inputDevPOC = inputDevPOC;
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

	public String getInputBounceReason() {
		return inputBounceReason;
	}

	public void setInputBounceReason(String inputBounceReason) {
		this.inputBounceReason = inputBounceReason;
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

	@Override
	public String toString() {
		return "BounceOutput [inputApplicationMAL=" + inputApplicationMAL + ", inputEnvironment=" + inputEnvironment
				+ ", inputBounceType=" + inputBounceType + ", inputDevPOC=" + inputDevPOC + ", inputProject="
				+ inputProject + ", inputReleaseTarget=" + inputReleaseTarget + ", inputRequestedDateTimeUTC="
				+ inputRequestedDateTimeUTC + ", inputRequestorId=" + inputRequestorId + ", inputSpecialRequest="
				+ inputSpecialRequest + ", inputBounceReason=" + inputBounceReason + ", resultMsg=" + resultMsg
				+ ", status=" + status + ", svcReqId=" + svcReqId + ", applicReqId=" + applicReqId
				+ ", applicReqStatusId=" + applicReqStatusId + "]";
	}

}