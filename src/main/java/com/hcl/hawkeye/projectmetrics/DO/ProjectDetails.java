package com.hcl.hawkeye.projectmetrics.DO;

import java.io.Serializable;
import java.util.List;

public class ProjectDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int programId;
	
	private List<ProjectInformation> projectInformation;

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public List<ProjectInformation> getProjectInformation() {
		return projectInformation;
	}

	public void setProjectInformation(List<ProjectInformation> projectInformation) {
		this.projectInformation = projectInformation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + programId;
		result = prime * result + ((projectInformation == null) ? 0 : projectInformation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectDetails other = (ProjectDetails) obj;
		if (programId != other.programId)
			return false;
		if (projectInformation == null) {
			if (other.projectInformation != null)
				return false;
		} else if (!projectInformation.equals(other.projectInformation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectDetails [programId=" + programId + ", projectInformation=" + projectInformation + "]";
	}
	
	

}
