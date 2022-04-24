package com.udemy.springgraphql.graphql.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.tools.SchemaParserOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GraphQLJavaToolsConfig {

    @Bean
    public SchemaParserOptions test(ObjectMapper objectMapper) {
        log.info("Configuring Schema parser option with modified Object Mapper");
        return SchemaParserOptions.newOptions()
                .objectMapperProvider(fieldDefinition -> objectMapper)
                .build();
    }
}