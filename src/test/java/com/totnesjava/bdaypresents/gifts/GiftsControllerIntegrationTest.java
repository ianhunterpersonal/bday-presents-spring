package com.totnesjava.bdaypresents.gifts;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import com.totnesjava.bdaypresents.BdayPresentsSpringApplication;

@SpringBootTest(classes = BdayPresentsSpringApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class GiftsControllerIntegrationTest {
	
//	@Autowired
//	private GiftRepository giftRepository;
	
	@LocalServerPort
	private int						port;

//	@Autowired
//	private TestRestTemplate	restTemplate;

//	@Sql({
//			"schema.sql", "data.sql"
//	})
	@Test
	public void testAllEmployees() {
		//Assertions.assertEquals(1, this.restTemplate.getForObject("http://localhost:" + port + "/v1/gifts", List.class).size());
		
	}
	

//	@Test
//	public void testAddEmployee() {
//		Credentials gift = Credentials.builder().build();
//		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/v1/gifts", gift, String.class);
//		assertEquals(201, responseEntity.getStatusCodeValue());
//	}
}
