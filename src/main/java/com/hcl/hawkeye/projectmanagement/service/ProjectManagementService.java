package com.hcl.hawkeye.projectmanagement.service;

import java.util.List;
import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;

/**
 * This Interface is intended to fetch the different kind of details 
 * from the JIRA with different formats
 * @author Haribabu
 *
 */
public interface ProjectManagementService {
	
	/**
	 * This method is used to get the which one is the current sprint and what are all the sprints in projects
	 * @param projectID
	 * @return
	 */
	SprintDetailsOfProject getProjectDetails(int projectID);
	
	/**
	 * In This method we will get all the details of the dashboard of the JIRA.
	 * It returns all board details available in JIRA
	 */
	DashBoardDetails getDashBoardInfo();
	
	/**
	 * This method used to average the Velocity of Project for all sprints based on the 
	 * estimated and completed story points
	 * @param projectId
	 * @return
	 */
	int getVelocityOfProject(int projectId);
	
	/**
	 * This method returns each sprint estimated, completed, status of sprint and id of sprint	
	 * @param ProjectId
	 * @return
	 */
	List<VelocityOfProject> getVelocityOfSprint(int ProjectId);
	
	/**
	 * This method returns for each sprint type of the defect types. if you specify issueType as Defect,
	 * This method will return how many defects are there in each sprint of project
	 * @param projectId
	 * @param issueType
	 * @return
	 */
	Map<String, Integer> getIssuesOfProject(int projectId, String issueType);
	
	/**
	 * This method returns for each sprint type of the defect types. if you specify issueType as Critical,
	 * This method will return how many Critical issues are there in each sprint of project
	 * @param projectId
	 * @param issuePriority
	 * @return
	 */
	Map<String, Map<String, Integer>> getPriorityOfIssue(int projectId, String blockerType, String criticalType);
	
	/**
	 * This method will return how many UAT issues are there in project
	 * @param project
	 * @return
	 */
	DefectTypes getDefectTypesOfProject(int project);

}
