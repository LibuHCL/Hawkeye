package com.hcl.hawkeye.projectmanagement.DO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Velocityinfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Sprints> sprints;
	private Map<String, Map<String, Map<String, Double>>> velocityStatEntries;

	/**
	 * @return the sprints
	 */
	public List<Sprints> getSprints() {
		return sprints;
	}

	/**
	 * @param sprints
	 *            the sprints to set
	 */
	public void setSprints(List<Sprints> sprints) {
		this.sprints = sprints;
	}

	/**
	 * @return the velocityStatEntries
	 */
	public Map<String, Map<String, Map<String, Double>>> getVelocityStatEntries() {
		return velocityStatEntries;
	}

	/**
	 * @param velData
	 *            the velocityStatEntries to set
	 */
	public void setVelocityStatEntries(Map<String, Map<String, Map<String, Double>>> velData) {
		this.velocityStatEntries = velData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sprints == null) ? 0 : sprints.hashCode());
		result = prime * result + ((velocityStatEntries == null) ? 0 : velocityStatEntries.hashCode());
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
		Velocityinfo other = (Velocityinfo) obj;
		if (sprints == null) {
			if (other.sprints != null)
				return false;
		} else if (!sprints.equals(other.sprints))
			return false;
		if (velocityStatEntries == null) {
			if (other.velocityStatEntries != null)
				return false;
		} else if (!velocityStatEntries.equals(other.velocityStatEntries))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Velocityinfo [sprints=" + sprints + ", velocityStatEntries=" + velocityStatEntries + "]";
	}

	
}
