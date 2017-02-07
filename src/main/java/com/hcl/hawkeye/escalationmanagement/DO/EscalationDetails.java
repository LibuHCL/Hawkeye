package com.hcl.hawkeye.escalationmanagement.DO;

public class EscalationDetails {
	
	private Integer qurter;
	private Integer count;
	
	public EscalationDetails(){
		
	}
	
	public EscalationDetails(Integer qurter,Integer count){
		this.qurter=qurter;
		this.count=count;
		}

	public Integer getQurter() {
		return qurter;
	}

	public void setQurter(Integer qurter) {
		this.qurter = qurter;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	
}
