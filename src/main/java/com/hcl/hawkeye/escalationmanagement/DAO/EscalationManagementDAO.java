package com.hcl.hawkeye.escalationmanagement.DAO;

import java.util.List;

import com.hcl.hawkeye.escalationmanagement.DO.Escalation;
import com.hcl.hawkeye.escalationmanagement.DO.EscalationDetails;

public interface EscalationManagementDAO {

	List<EscalationDetails> noOfEscAtProject(Escalation esc);

	Escalation capEscalationDetails(Escalation escalation);

	List<EscalationDetails> noOfEscPerQtAtProgram(Integer programId);

}
