package com.totnesjava.bdaypresents.persons;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.totnesjava.bdaypresents.gifts.GiftEntity;
import com.totnesjava.bdaypresents.gifts.GiftResource;

public class PersonEntityResourceMapperTest {

	@Test
	public void testEntityToResource() {
		
		List<GiftEntity> gifts = new ArrayList<>();
		gifts.add(GiftEntity.builder().title("gift1").build());
		
		PersonEntity entity = PersonEntity.builder()
				.id("_id_")
				.name("name")
				.email("email")
				.gifts(gifts)
				.build();
		
		PersonResource resource = PersonMapper.INSTANCE.map(entity);
		
		Assertions.assertEquals(entity.getId(),   resource.getId());
		Assertions.assertEquals(entity.getName(), resource.getName());
		Assertions.assertEquals(entity.getEmail(), resource.getEmail());
		Assertions.assertNotNull(resource.getGifts());
		Assertions.assertEquals(1, resource.getGifts().size());
		Assertions.assertEquals("gift1", resource.getGifts().get(0).getTitle());

	}
	
	@Test
	public void testResourceToEntity() {
		
		List<GiftResource> gifts = new ArrayList<>();
		gifts.add(GiftResource.builder().title("gift1").build());
		
		PersonResource resource = PersonResource.builder()
				.id("_id_")
				.name("name")
				.email("email")
				.gifts(gifts)
				.build();
		
		PersonEntity entity = PersonMapper.INSTANCE.map(resource);
		
		Assertions.assertEquals(resource.getId(),   entity.getId());
		Assertions.assertEquals(resource.getName(), entity.getName());
		Assertions.assertEquals(resource.getEmail(), entity.getEmail());
		Assertions.assertNotNull(resource.getGifts());
		Assertions.assertEquals(1, resource.getGifts().size());
		Assertions.assertEquals("gift1", resource.getGifts().get(0).getTitle());
		
	}
	
}
