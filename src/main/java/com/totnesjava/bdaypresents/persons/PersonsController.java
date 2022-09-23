package com.totnesjava.bdaypresents.persons;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totnesjava.bdaypresents.exceptions.BadUpdateRequestException;
import com.totnesjava.bdaypresents.exceptions.EntityNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApiResponses(value = {
		@ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content(schema = @Schema(implementation = Void.class)))
})
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/persons")
public class PersonsController {

	@Operation(summary = "Recovers persons in the database using optional filters as arguments")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Persons listed",
					content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = PersonResource.class)))
			)
	})
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<PersonResource>> fetchAll(@RequestParam(required = false) String email) {
		log.info("fetchAll persons");
		List<PersonResource> allPersons = service.findAll().stream().filter(pe -> ((email == null) || pe.getEmail().equals(email))).map((pe) -> PersonMapper.INSTANCE.map(pe)).collect(Collectors.toList());
		return ResponseEntity.ok(allPersons);
	}

	@Operation(summary = "Recover a person from their ID")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Person found",
					content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonResource.class))}),
			@ApiResponse(
					responseCode = "404",
					description = "Person not found",
					content = @Content
			)
	})
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<PersonResource> fetchById(@PathVariable String id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@Operation(summary = "Deletes person with given ID")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Person deleted"
			),
			@ApiResponse(
					responseCode = "404",
					description = "Person not found"
			)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		try {
			service.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException enfe) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Updates person with given ID with new attributes. Note the ID of the entity given is ignored.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Person updated",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResource.class))
			),
			@ApiResponse(
					responseCode = "404",
					description = "Person not found and could not be updated"
			)
	})
	@PutMapping("/{id}")
	public ResponseEntity<Void> replaceById(@PathVariable String id, @RequestBody PersonResource person) {
		try {
			service.replaceById(id, person);
			return ResponseEntity.ok().build();
		} catch (EntityNotFoundException enfe) {
			return ResponseEntity.notFound().build();
		} catch (BadUpdateRequestException brue) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Operation(summary = "Create a new person with given attributes.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "201",
					description = "Person created",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResource.class))
			)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<PersonResource> add(@RequestBody PersonResource personResource) {
		PersonResource toReturn = service.save(PersonMapper.INSTANCE.map(personResource));
		return ResponseEntity.created(URI.create("")).body(toReturn);
	}

	private PersonService service;

}
