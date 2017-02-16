/**
 * 
 */
package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author SamrajT
 *
 */
@ResponseStatus(value=HttpStatus.CONFLICT, reason="Failed to capture the happiness Details")
public class HappinessCaptureException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HappinessCaptureException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
