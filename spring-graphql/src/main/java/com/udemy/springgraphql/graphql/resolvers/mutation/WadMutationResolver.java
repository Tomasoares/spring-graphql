package com.udemy.springgraphql.graphql.resolvers.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udemy.springgraphql.graphql.resolvers.subscription.WadPublisher;
import com.udemy.springgraphql.graphql.type.WadInput;
import com.udemy.springgraphql.service.WadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class WadMutationResolver implements GraphQLMutationResolver {

    private final WadService service;

    public UUID createWad(WadInput input) {
        log.info("Creating wad: {}", input);
        UUID id = this.service.createWad(input);

        log.info("Created wad with id: {}", input);
        return id;
    }

}
