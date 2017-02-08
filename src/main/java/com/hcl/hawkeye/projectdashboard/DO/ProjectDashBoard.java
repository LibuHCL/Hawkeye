package com.hcl.hawkeye.projectdashboard.DO;

public class ProjectDashBoard {
	private Projects Projects;

    public Projects getProjects ()
    {
        return Projects;
    }

    public void setProjects (Projects Projects)
    {
        this.Projects = Projects;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Projects = "+Projects+"]";
    }

}
