package com.example.SpringbootElasticsearch2.repositories;

import com.example.SpringbootElasticsearch2.entities.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person, Integer> {
//    Iterable<Person> findAll(String query);
}
