package com.hcl.hawkeye.projectdashboard.DO;

import java.util.List;

public class DashBoardResource {
	private int count;

    private List<String> dev;

    private List<String> UIUX;

    private List<String> qa;

   

    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	

    public List<String> getDev() {
		return dev;
	}

	public void setDev(List<String> dev) {
		this.dev = dev;
	}

	public List<String> getUIUX() {
		return UIUX;
	}

	public void setUIUX(List<String> uIUX) {
		UIUX = uIUX;
	}

	public List<String> getQa() {
		return qa;
	}

	public void setQa(List<String> qa) {
		this.qa = qa;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", dev = "+dev+", UI/UX = "+UIUX+", qa = "+qa+"]";
    }

}
