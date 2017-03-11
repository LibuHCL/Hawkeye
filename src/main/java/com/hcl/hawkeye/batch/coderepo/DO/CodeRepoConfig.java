package com.hcl.hawkeye.batch.coderepo.DO;

public class CodeRepoConfig {
	private static final long serialVersionUID = 1L;

	private long projectId;
	private String toolName;
	private String toolHost;
	private String toolUrl;
	private String username;
	private String password;

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getToolHost() {
		return toolHost;
	}

	public void setToolHost(String toolHost) {
		this.toolHost = toolHost;
	}

	public String getToolUrl() {
		return toolUrl;
	}

	public void setToolUrl(String toolUrl) {
		this.toolUrl = toolUrl;
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

}
