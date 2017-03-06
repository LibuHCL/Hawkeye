package com.hcl.hawkeye.buildmanagement.DO;

import java.io.Serializable;

public class BuildStatisticsDetails implements Serializable {
	
	private int buildId;
	private int id;
	private String buildTool;
	private int projectId;
	private int buildNumber;
	private String planName;
	private String planShortName;
	private String planKey;
	private String buildResultKey;
	private String buildState;
	private String lifeCycleState;
	private String enabled;
	private String type;
	private String projectKey;
	private String projectName;
	private String isActive;
	private String buildName;
	private String isBuilding;
	private String buildStartedTime;
	private String buildCompletedTime;
	private int buildDurationInSeconds;
	private String vcsrevisionKey;
	private String buildTestSummary;
	private String buildReason;
	public BuildStatisticsDetails(int buildId, int id, String buildTool, int projectId, int buildNumber,
			String planName, String planShortName, String planKey, String buildResultKey, String buildState,
			String lifeCycleState, String enabled, String type, String projectKey, String projectName, String isActive,
			String buildName, String isBuilding, String buildStartedTime, String buildCompletedTime,
			int buildDurationInSeconds, String vcsrevisionKey, String buildTestSummary, String buildReason) {
		super();
		this.buildId = buildId;
		this.id = id;
		this.buildTool = buildTool;
		this.projectId = projectId;
		this.buildNumber = buildNumber;
		this.planName = planName;
		this.planShortName = planShortName;
		this.planKey = planKey;
		this.buildResultKey = buildResultKey;
		this.buildState = buildState;
		this.lifeCycleState = lifeCycleState;
		this.enabled = enabled;
		this.type = type;
		this.projectKey = projectKey;
		this.projectName = projectName;
		this.isActive = isActive;
		this.buildName = buildName;
		this.isBuilding = isBuilding;
		this.buildStartedTime = buildStartedTime;
		this.buildCompletedTime = buildCompletedTime;
		this.buildDurationInSeconds = buildDurationInSeconds;
		this.vcsrevisionKey = vcsrevisionKey;
		this.buildTestSummary = buildTestSummary;
		this.buildReason = buildReason;
	}
		
	public BuildStatisticsDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getBuildId() {
		return buildId;
	}
	public void setBuildId(int buildId) {
		this.buildId = buildId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuildTool() {
		return buildTool;
	}
	public void setBuildTool(String buildTool) {
		this.buildTool = buildTool;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(int buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanShortName() {
		return planShortName;
	}
	public void setPlanShortName(String planShortName) {
		this.planShortName = planShortName;
	}
	public String getPlanKey() {
		return planKey;
	}
	public void setPlanKey(String planKey) {
		this.planKey = planKey;
	}
	public String getBuildResultKey() {
		return buildResultKey;
	}
	public void setBuildResultKey(String buildResultKey) {
		this.buildResultKey = buildResultKey;
	}
	public String getBuildState() {
		return buildState;
	}
	public void setBuildState(String buildState) {
		this.buildState = buildState;
	}
	public String getLifeCycleState() {
		return lifeCycleState;
	}
	public void setLifeCycleState(String lifeCycleState) {
		this.lifeCycleState = lifeCycleState;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProjectKey() {
		return projectKey;
	}
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getIsBuilding() {
		return isBuilding;
	}
	public void setIsBuilding(String isBuilding) {
		this.isBuilding = isBuilding;
	}
	public String getBuildStartedTime() {
		return buildStartedTime;
	}
	public void setBuildStartedTime(String buildStartedTime) {
		this.buildStartedTime = buildStartedTime;
	}
	public String getBuildCompletedTime() {
		return buildCompletedTime;
	}
	public void setBuildCompletedTime(String buildCompletedTime) {
		this.buildCompletedTime = buildCompletedTime;
	}
	public int getBuildDurationInSeconds() {
		return buildDurationInSeconds;
	}
	public void setBuildDurationInSeconds(int buildDurationInSeconds) {
		this.buildDurationInSeconds = buildDurationInSeconds;
	}
	public String getVcsrevisionKey() {
		return vcsrevisionKey;
	}
	public void setVcsrevisionKey(String vcsrevisionKey) {
		this.vcsrevisionKey = vcsrevisionKey;
	}
	public String getBuildTestSummary() {
		return buildTestSummary;
	}
	public void setBuildTestSummary(String buildTestSummary) {
		this.buildTestSummary = buildTestSummary;
	}
	public String getBuildReason() {
		return buildReason;
	}
	public void setBuildReason(String buildReason) {
		this.buildReason = buildReason;
	}
	
	

}
