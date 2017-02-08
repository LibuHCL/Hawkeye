package com.hcl.hawkeye.programmanagement.service;

import java.util.List;

import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;

public interface ProgramManagementService {

	Program addProgram(Program program);

	List<Project> noOfProgramsInQuarter();

	Project addProjectsToProgram(Project project);

	List<Project> getProjectsPerProgramId(int progId);

	

}
