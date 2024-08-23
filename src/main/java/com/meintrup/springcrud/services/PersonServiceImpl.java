package com.meintrup.springcrud.services;

import com.meintrup.springcrud.entities.Person;
import com.meintrup.springcrud.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person storePerson(Person u) {
        return personRepository.save(u);
    }

    @Override
    public Optional<Person> loadPerson(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public Iterable<Person> loadAllPersons() {
        return personRepository.findAll();
    }
}
