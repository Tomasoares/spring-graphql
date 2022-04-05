package com.udemy.springgraphql.graphql.resolvers.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Wad;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String helloWorld() {
        return "Hello World is working!!1!";
    }

    public String findWad(String name, String author) {
        return String.format("Finding wad %s, by author %s", name, author);
    }

    public Wad wad() {
        return Wad.builder()
                .id(UUID.randomUUID())
                .name("Valiant")
                .build();
    }

    public List<String> topWads() {
        return Arrays.asList("Ozonia", "Eviternity", "Plutonia");
    }
}