package com.hcl.hawkeye.sonarmetrics.DO;

public class Trackers {

	private Double technicalDebt;
	private Integer blockers;
	private Integer critical;
	private Double complexity;
	private Integer commentedLines;
	private Double duplicateLines;
	private String sprint;

	public Trackers() {

	}

	public Trackers(Double technicalDebt, Integer blockers, Integer critical,
			Double complexity, Integer commentedLines, Double duplicateLines,
			String sprint) {
		super();
		this.technicalDebt = technicalDebt;
		this.blockers = blockers;
		this.critical = critical;
		this.complexity = complexity;
		this.commentedLines = commentedLines;
		this.duplicateLines = duplicateLines;
		this.sprint = sprint;
	}

	public Double getTechnicalDebt() {
		return technicalDebt;
	}

	public void setTechnicalDebt(Double technicalDebt) {
		this.technicalDebt = technicalDebt;
	}

	public Integer getBlockers() {
		return blockers;
	}

	public void setBlockers(Integer blockers) {
		this.blockers = blockers;
	}

	public Integer getCritical() {
		return critical;
	}

	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	public Double getComplexity() {
		return complexity;
	}

	public void setComplexity(Double complexity) {
		this.complexity = complexity;
	}

	public Integer getCommentedLines() {
		return commentedLines;
	}

	public void setCommentedLines(Integer commentedLines) {
		this.commentedLines = commentedLines;
	}

	public Double getDuplicateLines() {
		return duplicateLines;
	}

	public void setDuplicateLines(Double duplicateLines) {
		this.duplicateLines = duplicateLines;
	}

	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

}