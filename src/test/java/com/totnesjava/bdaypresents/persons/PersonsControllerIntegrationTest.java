package com.totnesjava.bdaypresents.persons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.totnesjava.bdaypresents.BdayPresentsSpringApplication;
import com.totnesjava.bdaypresents.gifts.GiftRepository;
import com.totnesjava.bdaypresents.gifts.GiftResource;

@SpringBootTest(classes = BdayPresentsSpringApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonsControllerIntegrationTest {

	@BeforeEach
	public void beforeEachTest() {
		baseUrl = "http://localhost:" + port + "/v1/persons";
		giftRepository.deleteAll();
		personRepository.deleteAll();
		Assertions.assertEquals(0, personRepository.count());
		Assertions.assertEquals(0, giftRepository.count());
	}

	@Test
	public void testAddPerson() {

		addPersonAndVerifyAdded("ian", "ian@email.com");

	}

	@Test
	public void testDeletePerson() {

		// Given
		String id = addPersonAndVerifyAdded("ian", "ian@email.com").getId();

		long numberRecordsInDb = personRepository.count();

		// When
		restTemplate.delete(baseUrl + "/" + id, new HashMap<>());

		// Then
		Assertions.assertEquals(numberRecordsInDb - 1, personRepository.count(), "Person count wrong");

	}

	@Test
	public void testDeleteNonExistantPerson() {

		// Given
		String id = addPersonAndVerifyAdded("ian", "ian@email.com").getId();
		long numberRecordsInDb = personRepository.count();

		// When
		restTemplate.delete(baseUrl + "/" + id + "_NOT", new HashMap<>());

		// Then
		Assertions.assertEquals(numberRecordsInDb, personRepository.count(), "Person count wrong");

	}

	@Test
	public void testReplacePersonWithIdInPathNotInObject() {

		// Given
		PersonResource origPerson = addPersonAndVerifyAdded("ian", "ian@email.com");
		String id = origPerson.getId();
		
		PersonResource updatedPerson = PersonResource.builder()
				.name("not_ian")
				.email("not_ian@email.com")
				.build();
		
		// When
		PersonResource fromServer = performPutAndGet(id, updatedPerson);
		
		// Then
		Assertions.assertEquals(1, personRepository.count(), "Person not deleted");

		
		Assertions.assertEquals(fromServer, PersonMapper.INSTANCE.map(personRepository.findById(id).get()));

	}

	@Test
	public void testReplacePersonWithIdInPathAndInObject() {

		// Given
		PersonResource origPerson = addPersonAndVerifyAdded("ian", "ian@email.com");
		String id = origPerson.getId();
		
		PersonResource updatedPerson = PersonResource.builder()
				.id(id)
				.name("not_ian")
				.email("not_ian@email.com")
				.build();
		
		// When
		PersonResource fromServer = performPutAndGet(id, updatedPerson);
		
		// Then
		Assertions.assertEquals(1, personRepository.count(), "Person not deleted");

		Assertions.assertEquals(fromServer, PersonMapper.INSTANCE.map(personRepository.findById(id).get()));

	}

	@Test
	public void testReplacePersonWithIdInPathAndInObjectAreDifferent() {

		// Given
		PersonResource origPerson = addPersonAndVerifyAdded("ian", "ian@email.com");
		String id = origPerson.getId();
		
		PersonResource updatedPerson = PersonResource.builder()
				.id(id + "_NOTSAME")
				.name("not_ian")
				.email("not_ian@email.com")
				.build();
		
		// When
		PersonResource fromServer = performPutAndGet(id, updatedPerson);
		
		// Then
		Assertions.assertEquals(1, personRepository.count(), "Person not deleted");

		Assertions.assertEquals(fromServer, PersonMapper.INSTANCE.map(personRepository.findById(id).get()));

	}

	@Test
	public void testDeleteUnknownPerson() {

		long originalDbCount = personRepository.count();

		// Given
		PersonResource personToAdd = addPersonAndVerifyAdded("ian", "ian@email.com");

		// When
		ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "/unknown_id", HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
		Assertions.assertEquals(404, response.getStatusCodeValue());
		
		// Then
		Assertions.assertEquals(originalDbCount + 1, personRepository.count(), "Person was deleted");

		Assertions.assertEquals(personToAdd, PersonMapper.INSTANCE.map(personRepository.findAll().get(0)));

	}

	@Test
	public void testFindAll() {

		PersonResource p1 = addPersonAndVerifyAdded("ian", "ian@email.com");
		PersonResource p2 = addPersonAndVerifyAdded("other", "other@email.com");
		Assertions.assertNotSame(p1, p2); // Sanity - just in case

		ResponseEntity<List<PersonResource>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<PersonResource>>() {
		});
		List<PersonResource> personsFromService = responseEntity.getBody();

		Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

		Assertions.assertEquals(2, personsFromService.size(), "Find all returns wrong number persons.");

		Assertions.assertTrue(personsFromService.contains(p1));
		Assertions.assertTrue(personsFromService.contains(p2));

	}

	@Test()
	public void testFindPersonById() {

		PersonResource thePerson = addPersonAndVerifyAdded("ian", "ian@email.com");

		String personId = thePerson.getId();

		String uri = baseUrl + "/" + personId;

		ResponseEntity<PersonResource> personFromService = this.restTemplate.getForEntity(uri, PersonResource.class);

		Assertions.assertEquals(200, personFromService.getStatusCodeValue());

		Assertions.assertEquals(thePerson, personFromService.getBody());

	}

	@Test()
	public void testFindPersonByEmail() {

		PersonResource thePerson1 = addPersonAndVerifyAdded("ian", "ian@email.com");

		addPersonAndVerifyAdded("other", "other@email.com"); // An addtional person to be ignored

		String uri = baseUrl + "?email=ian@email.com";

		ResponseEntity<List<PersonResource>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<PersonResource>>() {
		});
		List<PersonResource> personsFromService = responseEntity.getBody();

		Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

		Assertions.assertEquals(1, personsFromService.size(), "Find by email should only return one persons.");

		Assertions.assertEquals(thePerson1, personsFromService.get(0), "Wrong person returned");

	}

	private PersonResource addPersonAndVerifyAdded(String name, String email) {

		long originalDbCount = personRepository.count();

		PersonResource personToAdd = PersonResource.builder()
				.name(name)
				.email(email)
				.gifts(Collections.singletonList(GiftResource.builder().title("Gift for " + name).build()))
				.build();

		ResponseEntity<PersonResource> responseAddedEntity = this.restTemplate.postForEntity(baseUrl, personToAdd, PersonResource.class);
		assertEquals(201, responseAddedEntity.getStatusCodeValue());

		PersonResource addedPerson = responseAddedEntity.getBody();
		Assertions.assertEquals(1, addedPerson.getGifts().size());
		
		GiftResource gift = addedPerson.getGifts().get(0);
		Assertions.assertEquals(addedPerson.getId(), gift.getRecipientId());
		personToAdd.getGifts().get(0).setId(gift.getId());
		personToAdd.setId(addedPerson.getId());
		

		Assertions.assertEquals(originalDbCount + 1, personRepository.count());
		
		PersonEntity personFromDb = personRepository.findById(addedPerson.getId()).get();
		Assertions.assertEquals(name, personFromDb.getName());
		Assertions.assertEquals(email, personFromDb.getEmail());
		Assertions.assertEquals(addedPerson.getGifts().get(0).getRecipientId(), addedPerson.getId());

		return responseAddedEntity.getBody();

	}

	private PersonResource performPutAndGet(String id, PersonResource updatedPerson) {
		final String resourceUrl = baseUrl + "/" + id;
		restTemplate.put(resourceUrl, updatedPerson, PersonResource.class);
		PersonResource fromServer = restTemplate.getForObject(resourceUrl, PersonResource.class);
		return fromServer;
	}

	@Autowired
	private PersonRepository	personRepository;

	@Autowired
	private GiftRepository	   giftRepository;

	@LocalServerPort
	private int						port;

	@Autowired
	private TestRestTemplate	restTemplate;

	private String					baseUrl;

}
