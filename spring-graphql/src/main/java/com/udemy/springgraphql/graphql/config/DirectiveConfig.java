package com.udemy.springgraphql.graphql.config;

import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectiveConfig {

    @Bean
    public ValidationSchemaWiring validationSchemaWiring() {
        ValidationRules vr = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
                .build();

        return new ValidationSchemaWiring(vr);
    }
}
