/**
 * 
 */
package com.hcl.hawkeye.projectcost.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author HCL
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to retrieve the project cost details from db")
public class ProjectCostException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProjectCostException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
