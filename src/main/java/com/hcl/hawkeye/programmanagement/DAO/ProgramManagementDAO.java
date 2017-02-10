package com.hcl.hawkeye.programmanagement.DAO;

import java.util.List;

import com.hcl.hawkeye.portfolio.DO.Graph;
import com.hcl.hawkeye.portfolio.DO.Program;
import com.hcl.hawkeye.portfolio.DO.Project;

public interface ProgramManagementDAO {

	Program addProgram(Program prog);

	Project addProjectsToProgram(Project project);

	List<Project>  noOfProjectsInQuarter();

	List<Project> getProjectsPerProgramId(int progId);

	Project getProject(int projectId);

}
