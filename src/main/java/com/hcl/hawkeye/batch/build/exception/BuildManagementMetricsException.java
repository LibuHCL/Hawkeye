/**
 * 
 */
package com.hcl.hawkeye.batch.build.exception;

/**
 * @author Libu
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to push the Metrics data to AppDb")

public class BuildManagementMetricsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BuildManagementMetricsException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
