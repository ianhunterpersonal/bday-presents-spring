package com.totnesjava.bdaypresents.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse extends ResponseEntity<Object> {

	public ErrorResponse(HttpStatus status) {
		super(status);
		this.status = status.value();
	}

	public ErrorResponse(int status, String message) {
		super(HttpStatus.valueOf(status));
		this.status = status;
		this.message = message;
	}

	private int					status;
	private String				message = "";
	private String						stackTrace = "";
	private List<ValidationError>	errors = List.of();

	@Getter
	@Setter
	@RequiredArgsConstructor
	private static class ValidationError {
		private final String	field;
		private final String	message;
	}

	public void addValidationError(String field, String message) {
		if (Objects.isNull(errors)) {
			errors = new ArrayList<>();
		}
		errors.add(new ValidationError(field, message));
	}
}
