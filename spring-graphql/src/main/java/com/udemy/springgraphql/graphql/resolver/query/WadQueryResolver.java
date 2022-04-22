package com.udemy.springgraphql.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
