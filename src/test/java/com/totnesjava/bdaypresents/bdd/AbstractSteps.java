package com.totnesjava.bdaypresents.bdd;

import org.springframework.http.ResponseEntity;

public class AbstractSteps extends CucumberBootstrap {

	protected String baseUrl() {
		return "http://localhost:" + super.port;
	}

	protected <T> T callRestGet(String uri, Class<T> returnClass) {
		ResponseEntity<T> response = super.testRestTemplate.getForEntity(baseUrl() + uri, returnClass);
		lastHttpStatus = response.getStatusCodeValue();
		return response.getBody();
	}

	protected <T> T callRestPost(String uri, Object postObject, Class<T> returnClass) {
		ResponseEntity<T> response = super.testRestTemplate.postForEntity(baseUrl() + uri, postObject, returnClass);
		lastHttpStatus = response.getStatusCodeValue();
		return response.getBody();
	}

	protected Integer lastHttpStatus;
	
}
