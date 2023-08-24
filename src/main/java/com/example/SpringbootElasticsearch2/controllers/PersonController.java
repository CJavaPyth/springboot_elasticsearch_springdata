package com.example.SpringbootElasticsearch2.controllers;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.SpringbootElasticsearch2.entities.Person;
import com.example.SpringbootElasticsearch2.services.ElasticSearchService;
import com.example.SpringbootElasticsearch2.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apis")
public class PersonController {


    @Autowired
    private PersonService personService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    Iterable<Person> findAll() {
        return personService.getPeople();
    }

    @PostMapping("/insert")
    private Person insertPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("/update")
    private Person updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping("/delete/{personId}")
    private String deletePerson(@PathVariable int personId) {
        return personService.deletePerson(personId);
    }

//    @PostMapping("/searchPerson/{query}")
//    private Iterable<Person> searchPeople(@PathVariable String query) { return personService.searchPeople(query); }

    @GetMapping("/matchAll")
    public String matchAll() throws IOException {
        SearchResponse<Map> searchResponse = elasticSearchService.matchAllServices();
        System.out.println(searchResponse.hits().hits().toString());
        return searchResponse.hits().hits().toString();
    }

    @GetMapping("/matchAllPeople")
    public List<Person> matchAllPeople() throws IOException {
        SearchResponse<Person> searchResponse = elasticSearchService.matchAllPeopleService();
        System.out.println(searchResponse.hits().hits().toString());
        List<Hit<Person>> listOfHits = searchResponse.hits().hits();

        List<Person> listOfPeople = new ArrayList<>();

        for (Hit<Person> hit: listOfHits) {
            listOfPeople.add(hit.source());
        }

        return listOfPeople;
    }


    @GetMapping("/fuzzySearch/{query}")
    public List<Person> fuzzySearch(@PathVariable String query) throws IOException {
        SearchResponse<Person> searchResponse = elasticSearchService.fuzzySearch(query);

        List<Hit<Person>> listOfHits = searchResponse.hits().hits();
        System.out.println(listOfHits);
        List<Person> listOfPeople = new ArrayList<>();

        for (Hit<Person> hit : listOfHits) {
            listOfPeople.add(hit.source());
        }

        return listOfPeople;
    }

}
