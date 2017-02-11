package com.hcl.hawkeye.projectmanagement.DAO;

import java.util.Map;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;

/**
 * 
 * @author Haribabu
 *
 */
public interface ProjectManagementDAO {
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	SprintDetailsOfProject getProjectDetails(int projectId);
	
	/**
	 * 
	 * @return
	 */
	DashBoardDetails getDashBoardInfo();
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	Velocityinfo getVelocityOfProject( int projectId);
	
	/**
	 * 
	 * @param projectId
	 * @param issueType
	 * @return
	 */
	Map<String, Integer> getIssuesOfProject( int projectId, String issueType);
	
	/**
	 * 
	 * @param projectId
	 * @param issuePriority
	 * @return
	 */
	Map<String, Integer> getPriorityOfIssue( int projectId, String issuePriority);
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	DefectTypes getDefectTypesOfProject(int projectId);

}
