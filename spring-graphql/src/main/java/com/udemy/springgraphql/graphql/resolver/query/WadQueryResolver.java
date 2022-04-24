package com.udemy.springgraphql.graphql.resolver.query;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class WadQueryResolver implements GraphQLQueryResolver {

    private final WadService service;

    public List<Wad> getWads() {
        log.info("Retrieving all wads");
        final var wads = this.service.findAll();

        log.info("Returned wads: {}", wads);
        return wads;
    }

    public List<Wad> getWads(List<UUID> ids) {
        log.info("Retrieving all wads with ids: {}", ids);
        final var wads = this.service.findAll(ids);

        log.info("Returned wads: {}", wads);
        return wads;
    }

}
