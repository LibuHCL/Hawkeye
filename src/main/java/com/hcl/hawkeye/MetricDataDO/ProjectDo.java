package com.hcl.hawkeye.MetricDataDO;

import java.io.Serializable;

public class ProjectDo implements Serializable {
	
	private int  projectID;
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	private String  projectName;

}
