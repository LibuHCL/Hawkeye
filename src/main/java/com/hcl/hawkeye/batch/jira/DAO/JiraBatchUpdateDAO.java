package com.hcl.hawkeye.batch.jira.DAO;

import java.util.List;

import com.hcl.hawkeye.batch.jira.DO.Project;

public interface JiraBatchUpdateDAO {

	boolean insertProjectDetails(Project pj);
	
	boolean insertProjectDetails(List<Project> pj);
}
