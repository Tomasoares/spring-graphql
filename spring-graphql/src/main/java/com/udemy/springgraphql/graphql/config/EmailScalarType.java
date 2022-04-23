package com.udemy.springgraphql.graphql.config;

import graphql.language.StringValue;
import graphql.schema.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
@Slf4j
public class EmailScalarType {

    private static final String ERROR_MSG = "Email with invalid format!";

    @Bean
    public GraphQLScalarType email() {
        log.info("Configuring email scalar type");

        return new GraphQLScalarType("Email", "Custom email scalar type", new Coercing() {

            @Override
            public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
                log.info("Serializing Email type of {}", dataFetcherResult);

                if (dataFetcherResult instanceof String) {
                    return dataFetcherResult;
                }

                throw new CoercingSerializeException(ERROR_MSG);
            }

            @Override
            public Object parseValue(Object input) throws CoercingParseValueException {
                log.info("Parsing from email value of {}", input);

                if (input instanceof String) {

                    if (isEmailType(input.toString())) {
                        return input;
                    }
                }

                throw new CoercingParseValueException(ERROR_MSG);
            }

            @Override
            public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                log.info("Parsing from email literal of {}", input);

                if (input instanceof StringValue) {

                    if (isEmailType(input.toString())) {
                        return input.toString();
                    }
                }

                throw new CoercingParseLiteralException(ERROR_MSG);
            }
        });
    }

    protected boolean isEmailType(String email) {
        return Pattern.matches("[\\w]+@[\\w]+\\.[\\w]+", email);
    }
}
