package com.hcl.hawkeye.portfolio.DO;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Project {
	
	private int projectId;
	
	private String projName;
	
	private int progId;
	
	private int clientId;
	
	private int vendorId;
	
	private String projType;
	
	private String subType;
	
	private int techProjectManager;
	
	private String creationDate;
	
	private String endDate;
	
	private String status;
	
	private Integer quarter;
	
	private Integer count;
	
	public Project(){}
	public Project (int projectId,String projName,int progId,int clientId,int vendorId,String projType,String subType,
			int techProjectManager,String creationDate,String endDate, String status){
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
	

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
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

	public int getTechProjectManager() {
		return techProjectManager;
	}

	public void setTechProjectManager(int techProjectManager) {
		this.techProjectManager = techProjectManager;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
	

}

