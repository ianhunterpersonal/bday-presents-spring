package com.totnesjava.bdaypresents.gifts;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.totnesjava.bdaypresents.persons.PersonEntity;
import com.totnesjava.bdaypresents.persons.PersonService;

@ExtendWith(MockitoExtension.class)
public class GiftServiceTest {

	@Test
	public void testRetrieveGifts() {

		List<GiftEntity> expectedResults = Arrays.asList(GiftEntity.builder().build());

		when(mockGiftRepository.findAll()).thenReturn(expectedResults);

		List<GiftEntity> actualResults = sut.findAll();

		Assertions.assertSame(expectedResults, actualResults);

		Mockito.verify(mockGiftRepository).findAll();

	}

	@Test
	public void getMyGifts() {

		PersonEntity me = PersonEntity.builder().name("Ian").email("ian@mail.com").build();
		//PersonEntity other = PersonEntity.builder().name("Other").email("other@mail.com").build();

		List<GiftEntity> myGifts   = Arrays.asList(GiftEntity.builder().title("myGift_1").build());
		//List<GiftEntity> otherGifts = Arrays.asList(GiftEntity.builder().title("otherGift_1").build());
		
		me.setGifts(myGifts);
		
	}

	@Mock
	private GiftRepository	mockGiftRepository;

	@InjectMocks
	private GiftService		sut;

	@InjectMocks
	private PersonService		personService;
	
}
