package com.example.SpringbootElasticsearch2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "people")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    private int id;
    private String name;

    private int age;

    private String job_title;

    private String company;
    private String email_address;
    private String contact_num;
}
