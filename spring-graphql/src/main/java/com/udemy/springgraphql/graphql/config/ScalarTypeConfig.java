package com.udemy.springgraphql.graphql.config;

import graphql.scalars.ExtendedScalars;
import graphql.scalars.regex.RegexScalar;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class ScalarTypeConfig {

    @Bean
    public GraphQLScalarType date() {
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType time() { return ExtendedScalars.Time; }

    @Bean
    public GraphQLScalarType dateTime() { return ExtendedScalars.DateTime; }

    @Bean
    public RegexScalar phoneNumber() {
        return ExtendedScalars.newRegexScalar("PhoneNumber")
                .addPattern(Pattern.compile("[(]\\d{2}[)]*\\d{5}-\\d{4}"))
                .build();
    }

}
