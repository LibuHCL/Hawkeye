package com.hcl.hawkeye.MetricDataDO;


public class MetricData  {
	
	public MetricData() {
		super();
	}

	private String metric_Name;
	public MetricData(String metric_Name, String metric_Desc, String graph_Type, String screen_Name) {
		super();
		this.metric_Name = metric_Name;
		this.metric_Desc = metric_Desc;
		this.graph_Type = graph_Type;
		this.screen_Name = screen_Name;
	}

	private String metric_Desc;
	private String graph_Type;
	private String screen_Name;
	
	public String getMetric_Desc() {
		return metric_Desc;
	}
	public void setMetric_Desc(String metric_Desc) {
		this.metric_Desc = metric_Desc;
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

}
