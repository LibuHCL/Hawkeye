package com.hcl.hawkeye.escalationmanagement.DO;

public class Escalation {
	private Integer escalationId;
	private Integer projId;
	private String description;
	private Integer priority;
	private String escalationType;
	private Integer repResourceId;
	private Integer repCompanyId;
	private String repType;
	private Integer reporteeResId;
	private String escRepDate;
	private String resDate;
	private String resSolution;
	private String escStatus;
	
	public Escalation(){
		
	}
	
	public Escalation(Integer escalationId,Integer projId,String description,Integer priority,String escalationType,Integer repResourceId,
			Integer repCompanyId,String repType,Integer reporteeResId,String escRepDate,String resDate,String resSolution,String escStatus){

		this.escalationId=escalationId;
		this.projId=projId;
		this.description=description;
		this.priority=priority;
		this.escalationType=escalationType;
		this.repResourceId=repResourceId;
		this.repCompanyId=repCompanyId;
		this.repType=repType;
		this.reporteeResId=reporteeResId;
		this.escRepDate=escRepDate;
		this.resDate=resDate;
		this.resSolution=resSolution;
		this.escStatus=escStatus;
	}
	
	public Integer getProjId() {
		return projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public Integer getEscalationId() {
		return escalationId;
	}
	public void setEscalationId(Integer escalationId) {
		this.escalationId = escalationId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getEscalationType() {
		return escalationType;
	}
	public void setEscalationType(String escalationType) {
		this.escalationType = escalationType;
	}
	public Integer getRepResourceId() {
		return repResourceId;
	}
	public void setRepResourceId(Integer repResourceId) {
		this.repResourceId = repResourceId;
	}
	public Integer getRepCompanyId() {
		return repCompanyId;
	}
	public void setRepCompanyId(Integer repCompanyId) {
		this.repCompanyId = repCompanyId;
	}
	public String getRepType() {
		return repType;
	}
	public void setRepType(String repType) {
		this.repType = repType;
	}
	public Integer getReporteeResId() {
		return reporteeResId;
	}
	public void setReporteeResId(Integer reporteeResId) {
		this.reporteeResId = reporteeResId;
	}
	public String getEscRepDate() {
		return escRepDate;
	}
	public void setEscRepDate(String escRepDate) {
		this.escRepDate = escRepDate;
	}
	public String getResDate() {
		return resDate;
	}
	public void setResDate(String resDate) {
		this.resDate = resDate;
	}
	public String getResSolution() {
		return resSolution;
	}
	public void setResSolution(String resSolution) {
		this.resSolution = resSolution;
	}
	public String getEscStatus() {
		return escStatus;
	}
	public void setEscStatus(String escStatus) {
		this.escStatus = escStatus;
	}
	
	
	
	
}
