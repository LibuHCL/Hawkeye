package com.hcl.hawkeye.projectdashboard.DO;

public class Projects {
	private int runnningprojects;

    private Projectslist[] projectslist;

    public int getRunnningprojects ()
    {
        return runnningprojects;
    }

    public void setRunnningprojects (int runnningprojects)
    {
        this.runnningprojects = runnningprojects;
    }

    public Projectslist[] getProjectslist ()
    {
        return projectslist;
    }

    public void setProjectslist (Projectslist[] projectslist)
    {
        this.projectslist = projectslist;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [runnningprojects = "+runnningprojects+", projectslist = "+projectslist+"]";
    }

}
