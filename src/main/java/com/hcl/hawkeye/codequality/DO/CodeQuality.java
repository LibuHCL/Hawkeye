package com.hcl.hawkeye.codequality.DO;

import java.io.Serializable;

public class CodeQuality implements Serializable {
	
	private Double technicalDebt;
	private Integer blockerIssues;
	private Integer criticalIssues;
	private Double complexity;
	private Integer commentedLines;
	private Double duplicateLines;
	private String sprint;
	private Integer projectId;
	private String key;
	private Integer toolId;
	public Double getTechnicalDebt() {
		return technicalDebt;
	}
	public void setTechnicalDebt(Double technicalDebt) {
		this.technicalDebt = technicalDebt;
	}
	public Integer getBlockerIssues() {
		return blockerIssues;
	}
	public void setBlockerIssues(Integer blockerIssues) {
		this.blockerIssues = blockerIssues;
	}
	public Integer getCriticalIssues() {
		return criticalIssues;
	}
	public void setCriticalIssues(Integer criticalIssues) {
		this.criticalIssues = criticalIssues;
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
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getToolId() {
		return toolId;
	}
	public void setToolId(Integer toolId) {
		this.toolId = toolId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blockerIssues == null) ? 0 : blockerIssues.hashCode());
		result = prime * result + ((commentedLines == null) ? 0 : commentedLines.hashCode());
		result = prime * result + ((complexity == null) ? 0 : complexity.hashCode());
		result = prime * result + ((criticalIssues == null) ? 0 : criticalIssues.hashCode());
		result = prime * result + ((duplicateLines == null) ? 0 : duplicateLines.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((sprint == null) ? 0 : sprint.hashCode());
		result = prime * result + ((technicalDebt == null) ? 0 : technicalDebt.hashCode());
		result = prime * result + ((toolId == null) ? 0 : toolId.hashCode());
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
		CodeQuality other = (CodeQuality) obj;
		if (blockerIssues == null) {
			if (other.blockerIssues != null)
				return false;
		} else if (!blockerIssues.equals(other.blockerIssues))
			return false;
		if (commentedLines == null) {
			if (other.commentedLines != null)
				return false;
		} else if (!commentedLines.equals(other.commentedLines))
			return false;
		if (complexity == null) {
			if (other.complexity != null)
				return false;
		} else if (!complexity.equals(other.complexity))
			return false;
		if (criticalIssues == null) {
			if (other.criticalIssues != null)
				return false;
		} else if (!criticalIssues.equals(other.criticalIssues))
			return false;
		if (duplicateLines == null) {
			if (other.duplicateLines != null)
				return false;
		} else if (!duplicateLines.equals(other.duplicateLines))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (sprint == null) {
			if (other.sprint != null)
				return false;
		} else if (!sprint.equals(other.sprint))
			return false;
		if (technicalDebt == null) {
			if (other.technicalDebt != null)
				return false;
		} else if (!technicalDebt.equals(other.technicalDebt))
			return false;
		if (toolId == null) {
			if (other.toolId != null)
				return false;
		} else if (!toolId.equals(other.toolId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CodeQuality [technicalDebt=" + technicalDebt + ", blockerIssues=" + blockerIssues + ", criticalIssues="
				+ criticalIssues + ", complexity=" + complexity + ", commentedLines=" + commentedLines
				+ ", duplicateLines=" + duplicateLines + ", sprint=" + sprint + ", projectId=" + projectId + ", key="
				+ key + ", toolId=" + toolId + "]";
	}
	
}
