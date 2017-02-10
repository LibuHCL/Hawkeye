package com.hcl.hawkeye.sonarmetrics.DO;

public class Trackers {

	private Integer technicalDebt;
	private Integer blockers;
	private Integer critical;
	private Integer complexity;
	private Integer commentedLines;
	private Integer duplicateLines;

	public Trackers() {

	}

	public Trackers(Integer technicalDebt, Integer blockers, Integer critical, Integer complexity,
			Integer commentedLines, Integer duplicateLines) {
		super();
		this.technicalDebt = technicalDebt;
		this.blockers = blockers;
		this.critical = critical;
		this.complexity = complexity;
		this.commentedLines = commentedLines;
		this.duplicateLines = duplicateLines;
	}

	public Integer getTechnicalDebt() {
		return technicalDebt;
	}

	public void setTechnicalDebt(Integer technicalDebt) {
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

	public Integer getComplexity() {
		return complexity;
	}

	public void setComplexity(Integer complexity) {
		this.complexity = complexity;
	}

	public Integer getCommentedLines() {
		return commentedLines;
	}

	public void setCommentedLines(Integer commentedLines) {
		this.commentedLines = commentedLines;
	}

	public Integer getDuplicateLines() {
		return duplicateLines;
	}

	public void setDuplicateLines(Integer duplicateLines) {
		this.duplicateLines = duplicateLines;
	}

}
