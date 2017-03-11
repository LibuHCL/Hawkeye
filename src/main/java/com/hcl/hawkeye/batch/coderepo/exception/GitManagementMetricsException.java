package com.hcl.hawkeye.batch.coderepo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to push the GIT metrics data to AppDb")
public class GitManagementMetricsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GitManagementMetricsException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
