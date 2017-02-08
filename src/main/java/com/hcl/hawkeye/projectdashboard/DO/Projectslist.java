package com.hcl.hawkeye.projectdashboard.DO;

public class Projectslist {
	
	 private int id;
	 
	 private String name;
	 
	 private String startdate;
	 
	 private String enddate;
	 
	 private int risks;
	 
	 private int cost;
	 
	 private int schedule;
	 
	 private int quality;
	 
	 private String techmanager;
	 
	 private String programmanager;
	 
	 private int currentsprint;
	 
	 private int totalsprinits;
	 
	 private Resource[] resource;
	 
	 
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public int getRisks() {
		return risks;
	}


	public void setRisks(int risks) {
		this.risks = risks;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	public int getSchedule() {
		return schedule;
	}


	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}


	public int getQuality() {
		return quality;
	}


	public void setQuality(int quality) {
		this.quality = quality;
	}


	public String getTechmanager() {
		return techmanager;
	}


	public void setTechmanager(String techmanager) {
		this.techmanager = techmanager;
	}


	public String getProgrammanager() {
		return programmanager;
	}


	public void setProgrammanager(String programmanager) {
		this.programmanager = programmanager;
	}


	public int getCurrentsprint() {
		return currentsprint;
	}


	public void setCurrentsprint(int currentsprint) {
		this.currentsprint = currentsprint;
	}


	public int getTotalsprinits() {
		return totalsprinits;
	}


	public void setTotalsprinits(int totalsprinits) {
		this.totalsprinits = totalsprinits;
	}


	public Resource[] getResource() {
		return resource;
	}


	public void setResource(Resource[] resource) {
		this.resource = resource;
	}


	@Override
    public String toString()
    {
        return "ClassPojo [risks = "+risks+", resource = "+resource+", programmanager = "+programmanager+", cost = "+cost+", id = "+id+", schedule = "+schedule+", techmanager = "+techmanager+", startdate = "+startdate+", name = "+name+", quality = "+quality+", totalsprinits = "+totalsprinits+", enddate = "+enddate+", currentsprint = "+currentsprint+"]";
    }

}
