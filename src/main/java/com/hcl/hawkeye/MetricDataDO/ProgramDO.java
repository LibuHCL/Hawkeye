package com.hcl.hawkeye.MetricDataDO;

import java.io.Serializable;

public class ProgramDO implements Serializable {
	
	private int  programId;
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	private String  programName;

}
