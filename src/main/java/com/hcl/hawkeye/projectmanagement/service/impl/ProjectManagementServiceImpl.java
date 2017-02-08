package com.hcl.hawkeye.projectmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Sprints;
import com.hcl.hawkeye.projectmanagement.DO.StoryPoint;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;

@Service
public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
	ProjectManagementDAO pmDAO;
	
	@Override
	public SprintDetailsOfProject getProjectDetails(int projectId) {
		SprintDetailsOfProject proDetails = pmDAO.getProjectDetails(projectId);
		return proDetails;
	}

	@Override
	public DashBoardDetails getDashBoardInfo() {
		DashBoardDetails dashBoardInfo = pmDAO.getDashBoardInfo();
		return dashBoardInfo;
	}

	@Override
	public Double getVelocityOfProject(int projectId) {
		Velocityinfo vInfo = pmDAO.getVelocityOfProject(projectId);
		List<VelocityOfProject> velocityList = new ArrayList<VelocityOfProject>();
		if (null != vInfo && !vInfo.getSprints().isEmpty()) {
			for ( Sprints sprint  : vInfo.getSprints()) {
				VelocityOfProject velocityOfProject = new VelocityOfProject();
				velocityOfProject.setSprintId(sprint.getId());
				velocityOfProject.setSprintName(sprint.getName());
				velocityOfProject.setSprintState(sprint.getState());
				Map<String, Map<String,Double>> sMap = vInfo.getVelocityStatEntries().get(Integer.toString(sprint.getId()));
				for (String key : sMap.keySet()) {
					if ("estimated".equals(key)) {
						Map<String,Double> inMap = sMap.get(key);
						for (String inVal : inMap.keySet()) {
							if ("value".equals(inVal)) {
								velocityOfProject.setEstimatedValue(Double.valueOf(inMap.get(inVal)));
							}
						}
					} if ("completed".equals(key)) {
						Map<String,Double> inMap = sMap.get(key);
						for (String inVal : inMap.keySet()) {
							if ("value".equals(inVal)) {
								velocityOfProject.setCompletedValue(Double.valueOf(inMap.get(inVal)));
							}
						}
					}
				}
				velocityList.add(velocityOfProject);
			}
		}
		Double estimated = 0.0;
		Double completed = 0.0;
		for (VelocityOfProject velocityOfProject : velocityList) {
			System.out.println("@@@"+velocityOfProject.getEstimatedValue()+"$$$"+velocityOfProject.getCompletedValue());
			estimated +=  velocityOfProject.getEstimatedValue();
			completed +=  velocityOfProject.getCompletedValue();
		}
		
		System.out.println(estimated);
		System.out.println(completed);
		Double totalVelocity = (completed/estimated)*100;
		return totalVelocity;
	}

	@Override
	public Map<String, Integer> getIssuesOfProject(int projectId, String issueType) {
		Map<String, Integer> issues = pmDAO.getIssuesOfProject(projectId, issueType);
		return issues;
	}

	@Override
	public Integer getPriorityOfIssue(int projectId, String issuePriority) {
		Integer priorityIssue = pmDAO.getPriorityOfIssue(projectId, issuePriority);
		return priorityIssue;
	}

}
