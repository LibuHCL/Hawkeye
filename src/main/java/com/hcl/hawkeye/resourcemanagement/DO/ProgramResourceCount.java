package com.hcl.hawkeye.resourcemanagement.DO;

import java.io.Serializable;

public class ProgramResourceCount implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long count;
	private String projectID;
	private String projectName;
	private String location;
	private String resourceStatus;
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getResourceStatus() {
		return resourceStatus;
	}
	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((projectID == null) ? 0 : projectID.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((resourceStatus == null) ? 0 : resourceStatus.hashCode());
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
		ProgramResourceCount other = (ProgramResourceCount) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (projectID == null) {
			if (other.projectID != null)
				return false;
		} else if (!projectID.equals(other.projectID))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (resourceStatus == null) {
			if (other.resourceStatus != null)
				return false;
		} else if (!resourceStatus.equals(other.resourceStatus))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ProgramResourceCount [count=" + count + ", projectID=" + projectID + ", projectName=" + projectName
				+ ", location=" + location + ", resourceStatus=" + resourceStatus + "]";
	}
	
	
	
}
