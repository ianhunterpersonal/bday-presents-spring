package com.totnesjava.bdaypresents.sessions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Credentials {

	private String email;
	private String password;
	
}
