package com.hcl.hawkeye.batch.jira.DAO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.batch.jira.DO.Project;
import com.hcl.hawkeye.batch.jira.DO.SprintIssues;
import com.hcl.hawkeye.projectmanagement.DO.ProjectValues;

public interface JiraBatchUpdateDAO {
	boolean insertProjectDetails(Project pj);
	
	boolean insertProjectDetails(List<Project> pj);
	
	Map<Integer,String> getProjects();

	boolean insertSprinttDetails(List<ProjectValues> sprintsList);
	
	boolean insertIssueDetails(List<SprintIssues> pj);
	
	public String getFormattedDate(String time) throws ParseException;
	
	public String getFormatDate(String time) throws ParseException;
}
