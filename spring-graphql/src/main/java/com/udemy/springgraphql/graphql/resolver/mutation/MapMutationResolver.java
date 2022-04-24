package com.udemy.springgraphql.graphql.resolver.mutation;

import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.service.MapService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Log4j2
public class MapMutationResolver implements GraphQLMutationResolver {

    private final MapService service;

    public UUID createMap(final MapInput input) {
        log.info("Creating map: {}", input);
        final var id = this.service.createMap(input);

        log.info("Created map: {}", id);
        return id;
    }
}
