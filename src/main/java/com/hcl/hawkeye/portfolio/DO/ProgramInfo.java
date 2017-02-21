package com.hcl.hawkeye.portfolio.DO;

import java.sql.Timestamp;

public class ProgramInfo {
	
	
	private String programName;
	
	private Long projectsCount;
	
	private Timestamp startDate;
	private Timestamp endDate;
	
	private String status;

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Long getProjectsCount() {
		return projectsCount;
	}

	public void setProjectsCount(Long projectsCount) {
		this.projectsCount = projectsCount;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
