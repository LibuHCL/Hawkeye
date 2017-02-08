package com.hcl.hawkeye.projectdashboard.DO;

import java.util.List;

public class Projects {
	private int runnningprojects;

    private List<DashBoardProjectslist> projectslist;

    public int getRunnningprojects ()
    {
        return runnningprojects;
    }

    public void setRunnningprojects (int runnningprojects)
    {
        this.runnningprojects = runnningprojects;
    }

   
    public List<DashBoardProjectslist> getProjectslist() {
		return projectslist;
	}

	public void setProjectslist(List<DashBoardProjectslist> projectslist) {
		this.projectslist = projectslist;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [runnningprojects = "+runnningprojects+", projectslist = "+projectslist+"]";
    }

}
