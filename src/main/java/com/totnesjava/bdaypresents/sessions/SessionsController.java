package com.totnesjava.bdaypresents.sessions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totnesjava.bdaypresents.exceptions.PersonNotAuthenticatedException;
import com.totnesjava.bdaypresents.persons.PersonResource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiResponses(value = {
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
})
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/sessions")
public class SessionsController {

	@Operation(summary = "Logs a user into the system")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "Person authorised",
					content = @Content(mediaType = "application/json")
			),
			@ApiResponse(
				responseCode = "401",
				description = "Unauthorised",
				content = @Content(schema = @Schema(implementation = Void.class))
			)
	})
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public PersonResource login(@RequestBody Credentials credentials, HttpServletRequest request) throws PersonNotAuthenticatedException {
		log.info("Login with " + credentials);
		PersonResource loggedInPerson = service.login(credentials);
		return loggedInPerson;
	}

	private SessionService service;

}
