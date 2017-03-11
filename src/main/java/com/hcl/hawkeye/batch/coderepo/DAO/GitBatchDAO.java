package com.hcl.hawkeye.batch.coderepo.DAO;

import java.util.List;

import com.hcl.hawkeye.batch.coderepo.DO.CodeManagementRepo;

public interface GitBatchDAO {

	List<CodeManagementRepo> getAllCommitData();
	boolean insertGitCommitData(List<CodeManagementRepo> resource);
}
