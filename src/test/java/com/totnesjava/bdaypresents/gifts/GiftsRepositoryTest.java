package com.totnesjava.bdaypresents.gifts;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class GiftsRepositoryTest {

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
		GiftEntity toSave = GiftEntity.builder()
				.id(UUID.randomUUID().toString())
				.title("title").description("descr")
				.multiplicity(GiftMultiplicity.ONLY_ONE)
				.build();
		toSave = sut.save(toSave);
		Assertions.assertEquals(1, sut.count());
		Optional<GiftEntity> fromDb = sut.findById(toSave.getId());
		Assertions.assertTrue(fromDb.isPresent());
		Assertions.assertEquals(toSave, fromDb.get());
	}

	@Autowired
	private GiftRepository sut;

}
