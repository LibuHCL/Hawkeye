package com.hcl.hawkeye.batch.jira.DO;

import java.io.Serializable;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int toolProjectId;
	private String name;
	private String type;
	private String jiraUrl;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public String getJiraUrl() {
		return jiraUrl;
	}
	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}
	
	
	public int getToolProjectId() {
		return toolProjectId;
	}
	public void setToolProjectId(int toolProjectId) {
		this.toolProjectId = toolProjectId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", type=" + type + ", jiraUrl=" + jiraUrl + ", toolProjectId=" + toolProjectId + "]";
	}
	
	
}
