package com.hcl.hawkeye.projectdashboard.service;

import org.springframework.stereotype.Service;

import com.hcl.hawkeye.portfolio.DO.Project;
import com.hcl.hawkeye.projectdashboard.DO.ProjectDashBoard;

@Service
public interface ProjectDashBoardService {

	ProjectDashBoard getProjectDashBoard(Integer progId);

}
