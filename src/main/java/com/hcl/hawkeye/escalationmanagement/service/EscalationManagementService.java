package com.hcl.hawkeye.escalationmanagement.service;

import java.util.List;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.portfolio.DO.Graph;

public interface EscalationManagementService {

	Escalation capEscalationDetails(Escalation escalation);

	Graph noOfEscAtProject(int projId);

	List<Graph> noOfEscPerQtAtProgram(Integer programId);

}
