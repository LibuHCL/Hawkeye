/**
 * 
 */
package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author HCL
 *
 */
@ResponseStatus(value=HttpStatus.CONFLICT, reason="Failed to capture the happiness Details")
public class HappinessCaptureException extends RuntimeException {
	
	public HappinessCaptureException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
