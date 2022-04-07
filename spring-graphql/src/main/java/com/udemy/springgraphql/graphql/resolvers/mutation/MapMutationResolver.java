package com.udemy.springgraphql.graphql.resolvers.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.service.MapService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class MapMutationResolver implements GraphQLMutationResolver {

    private final MapService service;

    public MapMutationResolver(MapService service) {
        super();
        this.service = service;
    }

    public UUID createMap(MapInput input) {
        log.info("Creating map: {}", input);
        UUID id = this.service.createMap(input);

        log.info("Created map: {}", id);
        return id;
    }
}
