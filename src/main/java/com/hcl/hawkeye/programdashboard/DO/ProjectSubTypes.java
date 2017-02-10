package com.hcl.hawkeye.programdashboard.DO;

import java.util.List;

public class ProjectSubTypes {
	
	private String streamName;
	private int streamId;
	private int steaamProjectCount;
	private int redCount;
	private int yelloCount;
	private int greenCount;
	private List<ProjectDetails> projects;

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public int getSteaamProjectCount() {
		return steaamProjectCount;
	}

	public void setSteaamProjectCount(int steaamProjectCount) {
		this.steaamProjectCount = steaamProjectCount;
	}

	public int getRedCount() {
		return redCount;
	}

	public void setRedCount(int redCount) {
		this.redCount = redCount;
	}

	public int getYelloCount() {
		return yelloCount;
	}

	public void setYelloCount(int yelloCount) {
		this.yelloCount = yelloCount;
	}

	public int getGreenCount() {
		return greenCount;
	}

	public void setGreenCount(int greenCount) {
		this.greenCount = greenCount;
	}

	public List<ProjectDetails> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectDetails> projects) {
		this.projects = projects;
	}
	
	

}
