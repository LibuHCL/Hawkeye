package com.hcl.hawkeye.portfolio.DO;


import org.springframework.stereotype.Component;

@Component
public class Portfolio {
	
	private long portfolioId;
	
	private String portFolioName;
	
	private int clientId;
	
	private String creationDate;
	
	private String endDate;
	
	private String status;
	
	public Portfolio(){}
	public Portfolio(long portfolioId,String portFolioName,int clientId,String creationDate,String endDate, String status){
		this.portfolioId = portfolioId;
		this.portFolioName = portFolioName;
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.endDate = endDate;
		this.status = status;
	}

	public long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortFolioName() {
		return portFolioName;
	}

	public void setPortFolioName(String portFolioName) {
		this.portFolioName = portFolioName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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
