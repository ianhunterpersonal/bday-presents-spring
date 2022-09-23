package com.totnesjava.bdaypresents.persons;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Test
	public void testRetrievePersons() {
		
		List<PersonEntity> expectedResults = Arrays.asList(PersonEntity.builder().build());
		
		when(mockGiftRepository.findAll()).thenReturn(expectedResults);
		
		List<PersonEntity> actualResults = sut.findAll();
		
		Assertions.assertSame(expectedResults, actualResults);
		
		Mockito.verify(mockGiftRepository).findAll();
		
	}

	@Test
	public void testFindByIdWhenNotPresent() {

		String id = "dont_care";
		when(mockGiftRepository.findById(id)).thenReturn(Optional.empty());

		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
			sut.findById(id);
		});
		
		Assertions.assertEquals("Entity not found with ID=" + id, exception.getMessage());
		
	}
	

	@Mock
	private PersonRepository	mockGiftRepository;

	@InjectMocks
	private PersonService		sut;

}
