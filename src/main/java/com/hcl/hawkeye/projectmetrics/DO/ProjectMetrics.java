package com.hcl.hawkeye.projectmetrics.DO;

import java.util.List;

public class ProjectMetrics {
	
	private String projectname;
	
	private String EndDate;
	
	private String currentsprint;
	
	private String totalsptint;
	
	private String tpmname;
	
	private String pmname;
	
	private String developers;
	
	private String testers;
	
	private String uidev;
	
	private List <ProjectMetricResults> result;    

    public List<ProjectMetricResults> getResult() {
		return result;
	}

	public void setResult(List<ProjectMetricResults> result) {
		this.result = result;
	}

	public String getTotalsptint ()
    {
        return totalsptint;
    }

    public void setTotalsptint (String totalsptint)
    {
        this.totalsptint = totalsptint;
    }

    public String getEndDate ()
    {
        return EndDate;
    }

    public void setEndDate (String EndDate)
    {
        this.EndDate = EndDate;
    }

    public String getTesters ()
    {
        return testers;
    }

    public void setTesters (String testers)
    {
        this.testers = testers;
    }

    public String getPmname ()
    {
        return pmname;
    }

    public void setPmname (String pmname)
    {
        this.pmname = pmname;
    }

    public String getProjectname ()
    {
        return projectname;
    }

    public void setProjectname (String projectname)
    {
        this.projectname = projectname;
    }

    public String getUidev ()
    {
        return uidev;
    }

    public void setUidev (String uidev)
    {
        this.uidev = uidev;
    }

    public String getDevelopers ()
    {
        return developers;
    }

    public void setDevelopers (String developers)
    {
        this.developers = developers;
    }

    public String getTpmname ()
    {
        return tpmname;
    }

    public void setTpmname (String tpmname)
    {
        this.tpmname = tpmname;
    }

    public String getCurrentsprint ()
    {
        return currentsprint;
    }

    public void setCurrentsprint (String currentsprint)
    {
        this.currentsprint = currentsprint;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", totalsptint = "+totalsptint+", EndDate = "+EndDate+", testers = "+testers+", pmname = "+pmname+", projectname = "+projectname+", uidev = "+uidev+", developers = "+developers+", tpmname = "+tpmname+", currentsprint = "+currentsprint+"]";
    }

}
