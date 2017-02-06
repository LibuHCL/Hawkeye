package com.hcl.hawkeye.projectmanagement.DO;

import java.util.List;
import java.util.Map;

public class Velocityinfo {

	private List<Sprints> sprints;
	private Map<String, Map<String, StoryPoint>> velocityStatEntries;

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
	public Map<String, Map<String, StoryPoint>> getVelocityStatEntries() {
		return velocityStatEntries;
	}

	/**
	 * @param velData
	 *            the velocityStatEntries to set
	 */
	public void setVelocityStatEntries(Map<String, Map<String, StoryPoint>> velData) {
		this.velocityStatEntries = velData;
	}

}
