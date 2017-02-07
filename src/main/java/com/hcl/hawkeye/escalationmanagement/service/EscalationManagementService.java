package com.hcl.hawkeye.escalationmanagement.service;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;

public interface EscalationManagementService {

	Escalation capEscalationDetails(Escalation escalation);

	EscalationDetails noOfEscAtProject(Integer projectId);

}
