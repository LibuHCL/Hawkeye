package com.hcl.hawkeye.batch.coderepo.service;

import java.util.List;

import com.hcl.hawkeye.batch.coderepo.DO.CodeManagementRepo;

public interface GitCommitService {
	boolean insertGitCommitDetails(List<CodeManagementRepo> codeManagementRepo);

}
