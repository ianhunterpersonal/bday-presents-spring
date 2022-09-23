package com.totnesjava.bdaypresents.persons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.totnesjava.bdaypresents.gifts.GiftEntity;
import com.totnesjava.bdaypresents.gifts.GiftRepository;

@DataJpaTest
public class PersonsRepositoryTest {

	@BeforeEach
	public void beforeEachTest() {
		Assertions.assertEquals(0, sut.count());
	}

	/**
	 * Doesnt do anything except loads the schema and insert a record, and read
	 * back. This simply tests that the FlyWay migrations work.
	 */
	@Test
	public void testSchema() {
		GiftEntity giftToSave = GiftEntity.builder().title("Gift").description("Descr").build();
		PersonEntity toSave = PersonEntity.builder()
				.name("name")
				.email("email")
				.build();
		toSave.getGifts().add(giftToSave);
		giftToSave.setRecipient(toSave);
		
		toSave = sut.save(toSave);
		Assertions.assertEquals(1, sut.count());

		PersonEntity fromDb = sut.findById(toSave.getId()).get();
		Assertions.assertEquals(toSave, fromDb);
		
		String giftId = fromDb.getGifts().get(0).getId();
		GiftEntity giftFromDb = giftRepository.findById(giftId).get();
		Assertions.assertNotNull(giftFromDb.getRecipient());
	}

	@Autowired
	private PersonRepository sut;

	@Autowired
	private GiftRepository giftRepository;

}
