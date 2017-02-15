package com.hcl.hawkeye.projectmanagement.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.KanbanProDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;

/**
 * 
 * @author Haribabu
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManagementController.class);
	
	@Autowired
	ProjectManagementService pmService;
	
	/**
	 * This method is used to get the which one is the current sprint and what are all the sprints in projects
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/getProject/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public SprintDetailsOfProject getProjectData(@PathVariable("id") int projectId) {
		logger.info("Requesting api to get the project details with ID: {}", projectId);
		SprintDetailsOfProject proDetails = pmService.getProjectDetails(projectId);
		return proDetails;
	}
	
	/**
	 * This method returns the total dash board information of the JIRA 
	 * @return
	 */
	@RequestMapping(value="/getDashboard", method = RequestMethod.GET)
	@ResponseBody
	public DashBoardDetails getDashBoardData() {
		logger.info("Requesting api to get the dasboard information");
		DashBoardDetails dashBInfo = pmService.getDashBoardInfo();
		return dashBInfo;
	}
	
	/**
	 * This method returns the total velocity of the project for given project id
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/getVelocity/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public int getVelocityOfProject(@PathVariable("id") int projectId) {
		logger.info("Requesting api to get the velocity information of project id: {}", projectId);
		int velocityInfo = pmService.getVelocityOfProject(projectId);
		return velocityInfo;
	}
	
	/**
	 * This method resturns the list of sprints with estimated story points and completed story points with 
	 * sprint name and sprint id
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/getVelocityOfSprint/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<VelocityOfProject> getVelocityOfSprint(@PathVariable("id") int projectId) {
		logger.info("Requesting api to get the velocity information for sprint level with project id: {}", projectId);
		List<VelocityOfProject> velocityInfo = pmService.getVelocityOfSprint(projectId);
		return velocityInfo;
	}
	
	/**
	 * This method returns the number of issues in each sprint for given issue type. e.x. issueType = Story or Sub-task or Defect
	 * @param projectId
	 * @param issueType
	 * @return
	 */
	@RequestMapping(value="/getIssues/project/{id}/issueType/{issueType}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getIssuesOfProject(@PathVariable("id") int projectId, @PathVariable("issueType") String issueType) {
		logger.info("Requesting api to get the issues of project with project Id: {} and issueType: {}", projectId, issueType);
		Map<String, Integer> dashBInfo = pmService.getIssuesOfProject(projectId, issueType);
		return dashBInfo;
	}
	
	/**
	 * This method returns the issues for each sprint for the given priority. issuePriority is High or Critical or Medium.
	 * @param projectId
	 * @param issuePriority
	 * @return
	 */
	@RequestMapping(value="/getIssues/project/{id}/blocker/{blockerType}/critical/{criticalType}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Map<String, Integer>> getPriorityOfIssues(@PathVariable("id") int projectId, @PathVariable("blockerType") String blockerType, @PathVariable("criticalType") String criticalType) {
		logger.info("Requesting api to get the issues of project with project id: {} and priority of IssueTypes: {}",new Object[]{projectId, blockerType, criticalType});
		Map<String, Map<String, Integer>> priorityIssues = pmService.getPriorityOfIssue(projectId, blockerType, criticalType);
		return priorityIssues;
	}
	
	/**
	 * This method returns the number of valid issues and UAT issues in the project
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value="/getUATIssues/project/{id}", method = RequestMethod.GET)
	@ResponseBody
	public DefectTypes getDefectTypes(@PathVariable("id") int projectId) {
		logger.info("Requesting api to get the defect types of project with projectId: {}", projectId);
		DefectTypes dTypes = pmService.getDefectTypesOfProject(projectId);
		return dTypes;
	}

	@RequestMapping(value="/getKanbanProjectDetails/project/{projectId}", method = RequestMethod.GET)
	@ResponseBody
	public KanbanProDetails getKanbanProjectDetails(@PathVariable("projectId") int projectId) {
		logger.info("Requesting to get the project details for the given project id: {}", projectId);
		KanbanProDetails kDetails = pmService.getKanbanProjectDetails(projectId);
		return kDetails;
	}

}
