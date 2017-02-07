package com.hcl.hawkeye.portfolio.DO;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Project {
	
	private long projectId;
	
	private String projName;
	
	private int progId;
	
	private int clientId;
	
	private int vendorId;
	
	private String projType;
	
	private String subType;
	
	private String techProjectManager;
	
	private Date creationDate;
	
	private Date endDate;
	
	private String status;
	
	public Project(){}
	public Project (long projectId,String projName,int progId,int clientId,int vendorId,String projType,String subType,
			String techProjectManager,Date creationDate,Date endDate, String status){
		this.projectId=projectId;
		this.projName= projName;
		this.progId = progId;
		this.clientId = clientId;
		this.vendorId = vendorId;
		this.projType = projType;
		this.subType =subType;
		this.techProjectManager = techProjectManager;
		this.creationDate = creationDate;
		this.endDate = endDate;
		this.status = status;
		
	}
	

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getProjType() {
		return projType;
	}

	public void setProjType(String projType) {
		this.projType = projType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getTechProjectManager() {
		return techProjectManager;
	}

	public void setTechProjectManager(String techProjectManager) {
		this.techProjectManager = techProjectManager;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
