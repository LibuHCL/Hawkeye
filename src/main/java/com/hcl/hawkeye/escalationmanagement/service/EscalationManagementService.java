package com.hcl.hawkeye.escalationmanagement.service;

import java.util.List;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;

public interface EscalationManagementService {

	Escalation capEscalationDetails(Escalation escalation);

	List<EscalationDetails> noOfEscAtProject(Escalation esc);

	List<EscalationDetails> noOfEscPerQtAtProgram(Integer programId);

}
