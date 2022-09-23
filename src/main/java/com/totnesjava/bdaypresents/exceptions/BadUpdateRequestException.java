package com.totnesjava.bdaypresents.exceptions;

public class BadUpdateRequestException extends RuntimeException {

	public BadUpdateRequestException(Object id) {
		super("Bad update request for ID=" + String.valueOf(id));
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7779886847334216203L;

}
