package com.hcl.hawkeye.codequality.DAO;

import java.util.List;

import com.hcl.hawkeye.codequality.DO.Resource;

public interface CodeQualityDAO {
	
	Resource getSonarMetrics(String branchId);
	
	List<Resource> getAllSonarProjectDetails(String toolURL);

	void insertCodeQuality(Resource resource);

}
