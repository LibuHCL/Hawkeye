package com.hcl.hawkeye.projectmanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.hawkeye.projectmanagement.DAO.ProjectManagementDAO;
import com.hcl.hawkeye.projectmanagement.DO.DashBoardDetails;
import com.hcl.hawkeye.projectmanagement.DO.DefectTypes;
import com.hcl.hawkeye.projectmanagement.DO.SprintDetailsOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Sprints;
import com.hcl.hawkeye.projectmanagement.DO.VelocityOfProject;
import com.hcl.hawkeye.projectmanagement.DO.Velocityinfo;
import com.hcl.hawkeye.projectmanagement.service.ProjectManagementService;
import com.hcl.hawkeye.utils.HawkEyeUtils;

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
	public int getVelocityOfProject(int projectId) {
		Velocityinfo vInfo = pmDAO.getVelocityOfProject(projectId);
		List<VelocityOfProject> velocityList = getVelocityList(vInfo);
		Double estimated = 0.0;
		Double completed = 0.0;
		if(!velocityList.isEmpty()){
			for (VelocityOfProject velocityOfProject : velocityList) {
				estimated +=  velocityOfProject.getEstimatedValue();
				completed +=  velocityOfProject.getCompletedValue();
			}
		}else{
			return 0;
		}
		
		return HawkEyeUtils.getRAGStatus( (int)(completed/estimated)*100);
	}

	@Override
	public Map<String, Integer> getIssuesOfProject(int projectId, String issueType) {
		Map<String, Integer> issues = pmDAO.getIssuesOfProject(projectId, issueType);
		return issues;
	}

	@Override
	public Map<String, Integer> getPriorityOfIssue(int projectId, String issuePriority) {
		Map<String, Integer> priorityIssue = pmDAO.getPriorityOfIssue(projectId, issuePriority);
		return priorityIssue;
	}

	@Override
	public List<VelocityOfProject> getVelocityOfSprint(int projectId) {
		Velocityinfo vInfo = pmDAO.getVelocityOfProject(projectId);
		List<VelocityOfProject> velocityList = getVelocityList(vInfo);
		return velocityList;
	}
	
	private List<VelocityOfProject> getVelocityList(Velocityinfo vInfo) {
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
		return velocityList;
	}

	@Override
	public DefectTypes getDefectTypesOfProject(int project) {
		return null;
	}

}
