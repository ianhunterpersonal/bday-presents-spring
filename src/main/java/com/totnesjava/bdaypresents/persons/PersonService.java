package com.totnesjava.bdaypresents.persons;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totnesjava.bdaypresents.exceptions.EntityNotFoundException;
import com.totnesjava.bdaypresents.sessions.Credentials;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Service
public class PersonService {
	
	public List<PersonEntity> findAll() {
		return repository.findAll();
	}

	public PersonResource save(PersonEntity personEntity) {
		personEntity.getGifts().stream().forEach((g) -> g.setRecipient(personEntity));
		return PersonMapper.INSTANCE.map(repository.save(personEntity));
	}

	public PersonResource findById(String id) {
		Optional<PersonEntity> result = repository.findById(id);
		if (!result.isPresent()) throw new RuntimeException("Entity not found with ID=" + id);
		return(PersonMapper.INSTANCE.map(result.get()));
	}


	public PersonResource findByCredentials(Credentials credentials) {
		return PersonMapper.INSTANCE.map(repository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword()));
	}

	public void deleteById(String id) throws EntityNotFoundException {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFoundException(id);
		}
	}
	
	public void replaceById(String id, PersonResource person) {
		if (repository.existsById(id)) {
			person.setId(id);
			repository.save(PersonMapper.INSTANCE.map(person));
		} else {
			throw new EntityNotFoundException(id);
		}
	}

	@Autowired
	private PersonRepository repository;

}
