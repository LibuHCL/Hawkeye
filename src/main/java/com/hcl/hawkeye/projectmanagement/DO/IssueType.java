package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;

public class IssueType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String self;
	private String id;
	private String description;
	private String iconUrl;
	private String name;
	private boolean subtask;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * @param iconUrl the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
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
	 * @return the subtask
	 */
	public boolean isSubtask() {
		return subtask;
	}
	/**
	 * @param subtask the subtask to set
	 */
	public void setSubtask(boolean subtask) {
		this.subtask = subtask;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((iconUrl == null) ? 0 : iconUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((self == null) ? 0 : self.hashCode());
		result = prime * result + (subtask ? 1231 : 1237);
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IssueType [self=" + self + ", id=" + id + ", description=" + description + ", iconUrl=" + iconUrl
				+ ", name=" + name + ", subtask=" + subtask + "]";
	}

	
}
