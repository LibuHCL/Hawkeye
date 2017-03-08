package com.hcl.hawkeye.batch.build.DO;

import java.util.List;

public class Results{
	
    private List<Result> result;

    private String maxResult;

    private String expand;

    private String startIndex;

    private String size;

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
    
}
