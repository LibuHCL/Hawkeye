package com.hcl.hawkeye.escalationmanagement.DO;

public class EscalationDetails {
	
	//private Integer year;
	private Integer quarter;
	private Integer count;
	private String reason;
	
	
	

	public EscalationDetails(){
		
	}
	
	public EscalationDetails(Integer quarter,String reason,Integer count){
		this.quarter=quarter;
		this.count=count;
		this.reason=reason;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
}
