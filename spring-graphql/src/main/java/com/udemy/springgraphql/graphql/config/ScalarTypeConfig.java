package com.udemy.springgraphql.graphql.config;

import graphql.Scalars;
import graphql.scalars.ExtendedScalars;
import graphql.scalars.regex.RegexScalar;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLType;
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
    public GraphQLScalarType phoneNumber() {
        return ExtendedScalars.newRegexScalar("PhoneNumber")
                .addPattern(Pattern.compile("[(]\\d{2}[)]*\\d{5}-\\d{4}"))
                .build();
    }

    @Bean
    public GraphQLScalarType downloadLink() {
        return ExtendedScalars.newAliasedScalar("DownloadLink")
                .aliasedScalar(Scalars.GraphQLString)
                .build();
    }

}
