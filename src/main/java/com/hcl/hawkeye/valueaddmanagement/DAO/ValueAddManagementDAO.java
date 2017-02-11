package com.hcl.hawkeye.valueaddmanagement.DAO;

import java.util.ArrayList;
import java.util.Map;

import com.hcl.hawkeye.valueaddmanagement.DO.Value;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAdd;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueAddAcceptedIdeas;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreation;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueCreationQuarterly;
import com.hcl.hawkeye.valueaddmanagement.DO.ValueIndex;

public interface ValueAddManagementDAO {

	ValueAdd getNumbersOfValueAdd();
	
	Map<Integer, ValueIndex> getValueAddByIds(Integer portfolioId);

	Value createValueAdd(Value value);
	
	ValueAddAcceptedIdeas getValueAddByAcceptedIdeas(Integer programId);
	
	ValueCreation getValueCreationByProgramId(Integer programId);
	
	ValueCreation getValueCreationByProjectId(Integer projectId);
	
	ValueCreationQuarterly getQuarterlyValueByProjectId(Integer projectId);

	ValueCreationQuarterly getQuarterlyValueByProgramId(Integer programId);
	
	ValueAddAcceptedIdeas getEconomicValueAdd(Integer programId);

	ValueAddAcceptedIdeas getEconomicValueAddByPortfolio(Integer portfolioId);

}
