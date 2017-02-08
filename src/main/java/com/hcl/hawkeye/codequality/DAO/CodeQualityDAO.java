package com.hcl.hawkeye.codequality.DAO;

import com.hcl.hawkeye.codequality.DO.Resource;

public interface CodeQualityDAO {
	
	Resource getSonarMetrics(String branchId);

}
