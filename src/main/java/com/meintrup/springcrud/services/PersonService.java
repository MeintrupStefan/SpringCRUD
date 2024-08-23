package com.meintrup.springcrud.services;

import com.meintrup.springcrud.entities.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    // Stores user in db
    Person storePerson(Person u);

    // Loads user from db
    Optional<Person> loadPerson(UUID id);

    // Load all users from db
    Iterable<Person> loadAllPersons();
}
