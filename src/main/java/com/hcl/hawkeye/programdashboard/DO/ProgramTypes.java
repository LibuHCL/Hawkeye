package com.hcl.hawkeye.programdashboard.DO;

import java.util.List;

public class ProgramTypes {

	private String programName;
	private int programId;
	private List<ProjectStates> programSubArr;
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public List<ProjectStates> getProgramSubArr() {
		return programSubArr;
	}
	public void setProgramSubArr(List<ProjectStates> programSubArr) {
		this.programSubArr = programSubArr;
	}
	
	
}
