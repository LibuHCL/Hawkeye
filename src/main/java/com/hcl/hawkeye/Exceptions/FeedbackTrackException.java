package com.hcl.hawkeye.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Failed to insert hte data into db")
public class FeedbackTrackException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FeedbackTrackException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
