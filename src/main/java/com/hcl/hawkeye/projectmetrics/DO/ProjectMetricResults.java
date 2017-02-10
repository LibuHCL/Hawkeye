package com.hcl.hawkeye.projectmetrics.DO;

import java.util.List;

public class ProjectMetricResults {
	
	private String programName;
	
	private String programId;
	
	private List<Metrics> metrics; 
	
	private List<ProjectMetricCost> cost;
	
	private MetricsValueCreation valueCreation; 


    public List<Metrics> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}

	

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public List<ProjectMetricCost> getCost() {
		return cost;
	}

	public void setCost(List<ProjectMetricCost> cost) {
		this.cost = cost;
	}

	public MetricsValueCreation getValueCreation() {
		return valueCreation;
	}

	public void setValueCreation(MetricsValueCreation valueCreation) {
		this.valueCreation = valueCreation;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [metrics = "+metrics+", programId = "+programId+", programName = "+programName+"]";
    }

}
