package com.totnesjava.bdaypresents.exceptions;

public class EntityNotFoundException extends RuntimeException {

	
	public EntityNotFoundException(String id) {
		super("Entity not found ID=" + id);
	}
	
	private static final long serialVersionUID = -5352445252949337418L;

}
