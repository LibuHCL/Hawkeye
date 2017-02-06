package com.hcl.hawkeye.portfolio.DO;

import org.springframework.stereotype.Component;

@Component
public class Program {
	
	private Integer programId;
	
	private String programName;
	
	private Integer portfolioId;
	
	private int  clientId;
	
	private int progMangerId;

	private String creationDate;
	
	private String endDate;
	
	private String status;

	public Program(){}
	public Program(Integer programId,String programName,Integer portfolioId,int clientId,int progMangerId, String creationDate,String endDate, String status){
		
		this.programId = programId;
		this.programName = programName;	
		this.portfolioId = portfolioId;
		this.progMangerId = progMangerId;
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Integer getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getProgMangerId() {
		return progMangerId;
	}

	public void setProgMangerId(int progMangerId) {
		this.progMangerId = progMangerId;
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
	
}
