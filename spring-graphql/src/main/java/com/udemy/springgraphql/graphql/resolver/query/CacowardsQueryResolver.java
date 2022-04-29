package com.udemy.springgraphql.graphql.resolver.query;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class CacowardsQueryResolver implements GraphQLQueryResolver {

    private final WadService service;

    public List<Wad> cacowards(final int count, final int page) {
        log.info("Retrieving cacowards... count: {}, page: {}");
        final var cacowards = this.getCacowards(count, page);


        log.info("Retrieved cacowards: {}", cacowards);
        return cacowards;
    }

    private List<Wad> getCacowards(final int count, final int page) {
        return this.service.getCacowards(count, page);
    }

}
