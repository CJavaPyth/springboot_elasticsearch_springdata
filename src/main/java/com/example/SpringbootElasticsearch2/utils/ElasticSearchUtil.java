package com.example.SpringbootElasticsearch2.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import lombok.val;

import java.util.function.Supplier;


public class ElasticSearchUtil {

    public static Supplier<Query> supplier() {

        // supplies a method/value automatically to elasticsearch

        Supplier<Query> supplier = () -> Query.of(q-> q.matchAll(matchAllQuery()));

        return supplier;
    }

    public static MatchAllQuery matchAllQuery() {
        MatchAllQuery.Builder matchAllQuery;
        matchAllQuery = new MatchAllQuery.Builder();

        return matchAllQuery.build();
    }


    public static Supplier<Query> createSupplierQuery(String query) {
        Supplier<Query> supplier = ()->Query.of(q->q.fuzzy(createFuzzyQuery(query)));
        return supplier;
    }

    public static FuzzyQuery createFuzzyQuery(String query) {
        FuzzyQuery.Builder fuzzyQuery;
        fuzzyQuery = new FuzzyQuery.Builder();
        return fuzzyQuery.field("name").value(query).build();
    }
}
