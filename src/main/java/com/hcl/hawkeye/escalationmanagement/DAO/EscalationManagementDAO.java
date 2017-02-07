package com.hcl.hawkeye.escalationmanagement.DAO;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;

public interface EscalationManagementDAO {

	EscalationDetails noOfEscAtProject(Integer projectId);

	Escalation capEscalationDetails(Escalation escalation);

}
