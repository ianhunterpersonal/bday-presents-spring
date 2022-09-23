package com.totnesjava.bdaypresents.exceptions;

public class PersonNotAuthenticatedException extends RuntimeException {

	private static final long serialVersionUID = 6066106264696685322L;

	public PersonNotAuthenticatedException(String email) {
		super("Person not found with email = '" + email + "'");
	}
	
}
