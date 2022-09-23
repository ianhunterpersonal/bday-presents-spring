package com.totnesjava.bdaypresents.gifts;

import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.totnesjava.bdaypresents.persons.PersonEntity;

public class GiftMapperTest {

	@Test
	public void testGiftEntityToResource() {
		
		GiftEntity gift = GiftEntity.builder()
				.id("gift_id")
				.title("gift_title")
				.build();
		
		PersonEntity recipient = PersonEntity.builder()
				.id("person_id")
				.email("email")
				.name("name")
				.gifts(Collections.singletonList(gift))
				.build();
		
		gift.setRecipient(recipient);
		
		GiftResource giftResource = GiftMapper.INSTANCE.map(gift);

		Assertions.assertNotNull(giftResource.getRecipientId());
		
	}
}
