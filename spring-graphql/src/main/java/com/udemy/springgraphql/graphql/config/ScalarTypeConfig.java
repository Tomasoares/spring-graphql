package com.udemy.springgraphql.graphql.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScalarTypeConfig {

    @Bean
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }
}
