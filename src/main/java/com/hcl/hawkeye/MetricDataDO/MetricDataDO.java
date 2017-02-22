package com.hcl.hawkeye.MetricDataDO;

public class MetricDataDO {
	
	private String metric_Name;
	private String graph_Type;
	private String screen_Name;
	private int company_Id;
	private String company_Name;
	private int entity_Id;
	
	
    public MetricDataDO() {
		// TODO Auto-generated constructor stub
	}
	public String getMetric_Name() {
		return metric_Name;
	}
	public void setMetric_Name(String metric_Name) {
		this.metric_Name = metric_Name;
	}
	public String getGraph_Type() {
		return graph_Type;
	}
	public void setGraph_Type(String graph_Type) {
		this.graph_Type = graph_Type;
	}
	public String getScreen_Name() {
		return screen_Name;
	}
	public void setScreen_Name(String screen_Name) {
		this.screen_Name = screen_Name;
	}
	public int getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(int company_Id) {
		this.company_Id = company_Id;
	}
	public String getCompany_Name() {
		return company_Name;
	}
	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}
	public int getEntity_Id() {
		return entity_Id;
	}
	public void setEntity_Id(int entity_Id) {
		this.entity_Id = entity_Id;
	}
	public MetricDataDO(String metric_Name, String graph_Type, String screen_Name, int company_Id, String company_Name,
			int entity_Id) {
		super();
		this.metric_Name = metric_Name;
		this.graph_Type = graph_Type;
		this.screen_Name = screen_Name;
		this.company_Id = company_Id;
		this.company_Name = company_Name;
		this.entity_Id = entity_Id;
	}
	
	
		
	

}
