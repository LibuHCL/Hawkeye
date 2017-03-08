package com.hcl.hawkeye.batch.build.DO;

public class Result {
	
    private String id;

    private String buildNumber;

    private Plan plan;

    private PlanResultKey planResultKey;

    private Link link;

    private String state;

    private String buildResultKey;

    private String number;

    private String buildState;

    private String lifeCycleState;

    private String key;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public PlanResultKey getPlanResultKey() {
		return planResultKey;
	}

	public void setPlanResultKey(PlanResultKey planResultKey) {
		this.planResultKey = planResultKey;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBuildResultKey() {
		return buildResultKey;
	}

	public void setBuildResultKey(String buildResultKey) {
		this.buildResultKey = buildResultKey;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    
	
}
