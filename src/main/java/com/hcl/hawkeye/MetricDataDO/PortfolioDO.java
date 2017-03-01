package com.hcl.hawkeye.MetricDataDO;

import java.io.Serializable;

public class PortfolioDO implements Serializable {
	
	private int  portfolio_Id;
	private int  programId;
	private String Portfolio_name;
	
	public String getPortfolio_name() {
		return Portfolio_name;
	}

	public void setPortfolio_name(String portfolio_name) {
		Portfolio_name = portfolio_name;
	}

	public int getPortfolio_Id() {
		return portfolio_Id;
	}

	public void setPortfolio_Id(int portfolio_Id) {
		this.portfolio_Id = portfolio_Id;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public PortfolioDO(int portfolio_Id, int programId) {
		super();
		this.portfolio_Id = portfolio_Id;
		this.programId = programId;
	}

	public PortfolioDO() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
