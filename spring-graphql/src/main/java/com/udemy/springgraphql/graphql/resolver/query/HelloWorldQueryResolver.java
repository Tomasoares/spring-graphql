package com.udemy.springgraphql.graphql.resolver.query;

import com.udemy.springgraphql.graphql.type.Wad;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String helloWorld(String phoneNumber, String email) {
        return "Hello World is working!!1!";
    }

    public String getDeveloperEmail() {
        return "tomassoares@gmail.com";
    }

    public String findWad(String name, String author) {
        return String.format("Finding wad %s, by author %s", name, author);
    }

    public Wad wad() {
        return Wad.builder()
                .id(UUID.randomUUID())
                .name("Valiant")
                .released(LocalDate.now())
                .build();
    }

    public List<String> topWads() {
        return Arrays.asList("Ozonia", "Eviternity", "Plutonia");
    }
}
