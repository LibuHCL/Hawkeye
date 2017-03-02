package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class ProjectValues implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int toolProjectId;
	private String self;
	private String state;
	private String name;
	private String startDate;
	private String endDate;
	private String completeDate;
	private int originBoardId;
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
	 * @return the self
	 */
	public String getSelf() {
		return self;
	}
	/**
	 * @param self the self to set
	 */
	public void setSelf(String self) {
		this.self = self;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the completeDate
	 */
	public String getCompleteDate() {
		return completeDate;
	}
	/**
	 * @param completeDate the completeDate to set
	 */
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	/**
	 * @return the originBoardId
	 */
	public int getOriginBoardId() {
		return originBoardId;
	}
	/**
	 * @param originBoardId the originBoardId to set
	 */
	public void setOriginBoardId(int originBoardId) {
		this.originBoardId = originBoardId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	public int getToolProjectId() {
		return toolProjectId;
	}
	public void setToolProjectId(int toolProjectId) {
		this.toolProjectId = toolProjectId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + originBoardId;
		result = prime * result + ((self == null) ? 0 : self.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectValues other = (ProjectValues) obj;
		if (completeDate == null) {
			if (other.completeDate != null)
				return false;
		} else if (!completeDate.equals(other.completeDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (originBoardId != other.originBoardId)
			return false;
		if (self == null) {
			if (other.self != null)
				return false;
		} else if (!self.equals(other.self))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProjectValues [id=" + id + ", self=" + self + ", state=" + state + ", name=" + name + ", startDate="
				+ startDate + ", endDate=" + endDate + ", completeDate=" + completeDate + ", originBoardId="
				+ originBoardId + "]";
	}
	
	
}
