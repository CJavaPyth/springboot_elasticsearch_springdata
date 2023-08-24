package com.example.SpringbootElasticsearch2.services;

import com.example.SpringbootElasticsearch2.entities.Person;
import com.example.SpringbootElasticsearch2.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }
    public String deletePerson(int id) {
        personRepository.deleteById(id);
        return "Person deleted";
    }

//    public Iterable<Person> searchPeople(String query) {
//        return personRepository.findAll(query);
//    }

}
