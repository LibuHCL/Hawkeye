package com.hcl.hawkeye.escalationmanagement.DAO;

import java.util.List;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.portfolio.DO.Graph;

public interface EscalationManagementDAO {

	Graph noOfEscAtProject(int projectId);

	Escalation capEscalationDetails(Escalation escalation);

	List<Graph> noOfEscPerQtAtProgram(Integer programId);

}
