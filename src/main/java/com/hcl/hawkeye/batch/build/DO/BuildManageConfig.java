package com.hcl.hawkeye.batch.build.DO;
/*
 * Samraj T
 */
public class BuildManageConfig {
	
	private double projectid;
	private String toolProjectId;
	private double buildNoLatest;
	private double hawkeyeBuildNo;
	private String toolname;
	private String toolhost;
	private String toolurl;
	private String username;
	private String password;
	
	
	public double getProjectid() {
		return projectid;
	}


	public void setProjectid(double projectid) {
		this.projectid = projectid;
	}


	public String getToolProjectId() {
		return toolProjectId;
	}


	public void setToolProjectId(String toolProjectId) {
		this.toolProjectId = toolProjectId;
	}


	public double getBuildNoLatest() {
		return buildNoLatest;
	}


	public void setBuildNoLatest(double buildNoLatest) {
		this.buildNoLatest = buildNoLatest;
	}


	public double getHawkeyeBuildNo() {
		return hawkeyeBuildNo;
	}


	public void setHawkeyeBuildNo(double hawkeyeBuildNo) {
		this.hawkeyeBuildNo = hawkeyeBuildNo;
	}


	public String getToolname() {
		return toolname;
	}


	public void setToolname(String toolname) {
		this.toolname = toolname;
	}


	public String getToolhost() {
		return toolhost;
	}


	public void setToolhost(String toolhost) {
		this.toolhost = toolhost;
	}


	public String getToolurl() {
		return toolurl;
	}


	public void setToolurl(String toolurl) {
		this.toolurl = toolurl;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public BuildManageConfig() {
		super();
	}


	public BuildManageConfig(double projectid, String toolProjectId,double hawkeyeBuildNo,
			String toolname, String toolhost, String toolurl, String username, String password) {
		super();
		this.projectid = projectid;
		this.toolProjectId = toolProjectId;
		this.hawkeyeBuildNo = hawkeyeBuildNo;
		this.toolname = toolname;
		this.toolhost = toolhost;
		this.toolurl = toolurl;
		this.username = username;
		this.password = password;
	}
	
}
