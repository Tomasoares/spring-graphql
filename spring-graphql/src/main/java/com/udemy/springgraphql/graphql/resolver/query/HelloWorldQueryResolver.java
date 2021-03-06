package com.udemy.springgraphql.graphql.resolver.query;

import com.udemy.springgraphql.graphql.config.context.CustomGraphQLContext;
import com.udemy.springgraphql.graphql.type.Wad;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String helloWorld(String phoneNumber, String email, DataFetchingEnvironment env) {
        log.info("Generated the following execution Id: {}", env.getExecutionId());
        return "Hello World is working!!1!";
    }

    public String getDeveloperEmail() {
        return "tomassoares@gmail.com";
    }

    public String findWad(String name, String author) {
        return String.format("Finding wad %s, by author %s", name, author);
    }

    public Wad wad(DataFetchingEnvironment env) {
        var fields = env.getSelectionSet().getFields().stream().map(f -> f.getName()).collect(Collectors.toList());
        log.info("Selected fields: {}", fields);
        log.info("User Id: {}", ((CustomGraphQLContext) env.getContext()).getUserId());

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
