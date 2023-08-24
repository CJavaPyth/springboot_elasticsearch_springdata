package com.example.SpringbootElasticsearch2.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.SpringbootElasticsearch2.entities.Person;
import com.example.SpringbootElasticsearch2.utils.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticSearchClient;

    public SearchResponse<Map> matchAllServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse = elasticSearchClient.search(s-> s.query(supplier.get()), Map.class);
        System.out.println("elasticsearch query is: " + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Person> matchAllPeopleService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Person> searchResponse = elasticSearchClient.search(s-> s.index("people").query(supplier.get()), Person.class);
        System.out.println("elasticsearch query is: " + supplier.get().toString());
        return searchResponse;
    }

    public SearchResponse<Person> fuzzySearch(String query) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.createSupplierQuery(query);
        SearchResponse<Person> response = elasticSearchClient.search(s->s.index("people").query(supplier.get()), Person.class);

        System.out.println("elasticsearch supplier fuzzy query: " + supplier.get().toString());
        return response;
    }
}
