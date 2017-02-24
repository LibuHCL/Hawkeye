package com.hcl.hawkeye.MetricDataDO;

import java.io.Serializable;

public class MetricConfiguration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String graphType;
	
	private String screenName;
	
	private String metricName;

	public String getGraphType() {
		return graphType;
	}

	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graphType == null) ? 0 : graphType.hashCode());
		result = prime * result + ((metricName == null) ? 0 : metricName.hashCode());
		result = prime * result + ((screenName == null) ? 0 : screenName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricConfiguration other = (MetricConfiguration) obj;
		if (graphType == null) {
			if (other.graphType != null)
				return false;
		} else if (!graphType.equals(other.graphType))
			return false;
		if (metricName == null) {
			if (other.metricName != null)
				return false;
		} else if (!metricName.equals(other.metricName))
			return false;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetricConfiguration [graphType=" + graphType + ", screenName=" + screenName + ", metricName="
				+ metricName + "]";
	}
	
	
	
}
