package com.hcl.hawkeye.programdashboard.DO;

import java.util.List;

public class ProjectStates {

	private String programStatus;
	private int programCount;
	private List<ProjectSubTypes> stream;
	public String getProgramStatus() {
		return programStatus;
	}
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	public int getProgramCount() {
		return programCount;
	}
	public void setProgramCount(int programCount) {
		this.programCount = programCount;
	}
	public List<ProjectSubTypes> getStream() {
		return stream;
	}
	public void setStream(List<ProjectSubTypes> stream) {
		this.stream = stream;
	}
	
	
}
