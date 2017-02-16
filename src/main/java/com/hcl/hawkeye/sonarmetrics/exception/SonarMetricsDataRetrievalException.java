/**
 * 
 */
package com.hcl.hawkeye.sonarmetrics.exception;

/**
 * @author HCL
 *
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Failed to retrieve the Sonar Metrics data from db")
public class SonarMetricsDataRetrievalException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public SonarMetricsDataRetrievalException(String msg, Throwable cause) {
		super(msg, cause);
	}

}