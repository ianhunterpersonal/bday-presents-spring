package com.totnesjava.bdaypresents.persons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,String> {

	PersonEntity findByEmailAndPassword(String email, String password);

}
