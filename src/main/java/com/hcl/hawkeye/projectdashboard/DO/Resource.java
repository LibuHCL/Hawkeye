package com.hcl.hawkeye.projectdashboard.DO;

public class Resource {
	private int count;

    private String[] dev;

    private String[] UIUX;

    private String[] qa;

   

    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String[] getDev ()
    {
        return dev;
    }

    public void setDev (String[] dev)
    {
        this.dev = dev;
    }

    public String[] getUIUX ()
    {
        return UIUX;
    }

    public void setUIUX (String[] UIUX)
    {
        this.UIUX = UIUX;
    }

    public String[] getQa ()
    {
        return qa;
    }

    public void setQa (String[] qa)
    {
        this.qa = qa;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", dev = "+dev+", UI/UX = "+UIUX+", qa = "+qa+"]";
    }

}
