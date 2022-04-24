package com.udemy.springgraphql.graphql.resolver.mutation;

import com.udemy.springgraphql.graphql.type.WadInput;
import com.udemy.springgraphql.service.WadService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class WadMutationResolver implements GraphQLMutationResolver {

    private final WadService service;

    public UUID createWad(final WadInput input) {
        log.info("Creating wad: {}", input);
        final var id = this.service.createWad(input);

        log.info("Created wad with id: {}", input);
        return id;
    }

}
