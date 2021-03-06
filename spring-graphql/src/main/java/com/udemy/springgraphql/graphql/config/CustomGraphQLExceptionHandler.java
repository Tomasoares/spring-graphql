package com.udemy.springgraphql.graphql.config;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphQLExceptionHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(final List<GraphQLError> errors) {
        return errors.stream().map(this::getError).collect(Collectors.toList());
    }

    private GraphQLError getError(final GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching && ((ExceptionWhileDataFetching) error).getException() instanceof GraphQLError) {
            return (GraphQLError) ((ExceptionWhileDataFetching) error).getException();
        }

        return error;
    }

}
