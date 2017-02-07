package com.hcl.hawkeye.escalationmanagement.DO;

public class EscalationDetails {
	
	private Integer quarter;
	private Integer count;
	
	public EscalationDetails(){
		
	}
	
	public EscalationDetails(Integer quarter,Integer count){
		this.quarter=quarter;
		this.count=count;
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
