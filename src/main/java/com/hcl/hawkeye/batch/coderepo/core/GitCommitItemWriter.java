package com.hcl.hawkeye.batch.coderepo.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hcl.hawkeye.batch.coderepo.DO.CodeManagementRepo;
import com.hcl.hawkeye.batch.coderepo.service.GitCommitService;
import com.hcl.hawkeye.utils.HawkEyeUtils;

@Configuration
@PropertySource("classpath:config.properties")
public class GitCommitItemWriter implements ItemWriter<List<CodeManagementRepo>> {

	private static final Logger logger = LoggerFactory.getLogger(GitCommitItemWriter.class);

	@Autowired
	GitCommitService gitCommitService;

	@Autowired
	Environment env;

	private StepExecution stepExecution;

	List<CodeManagementRepo> codeManagementresources = new ArrayList<>();

	List<String> metricsList = new ArrayList<String>();

	@Override
	public void write(List<? extends List<CodeManagementRepo>> codeManagementresources) throws Exception {
		logger.info("The size got from Item Reader  : {}", codeManagementresources.size());
		List<CodeManagementRepo> resultList = new ArrayList<>();
		if (null != codeManagementresources) {
			for (List<CodeManagementRepo> resource : codeManagementresources) {
				for (CodeManagementRepo values : resource) {
					CodeManagementRepo result = new CodeManagementRepo();
					result.setId(values.getId());
					result.setAuthor_name(values.getAuthor_name());
					result.setAuthor_email(values.getAuthor_email());
					result.setDate(values.getDate());
					//result.setCommitDate(HawkEyeUtils.getTimeStamp(values.getDate()));
					result.setBranchName(values.getBranchName());
					result.setProjectId(CodeManagementRepo.getProjectConfId());
					resultList.add(result);
				}
			}

			try {
				boolean insertStatus = gitCommitService.insertGitCommitDetails(resultList);
				if (insertStatus) {
					logger.info("Git build data Inserted succesfully");
					ExecutionContext stepContext = this.stepExecution.getExecutionContext();
					stepContext.put("buildDetails", resultList);
				} else {
					logger.error("Already existing data present");
				}
			} catch (Exception e) {
				logger.info("unable to insert the data");
			}
		}

	}

}
