package com.udemy.springgraphql.graphql.queryresolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class WadQueryResolver implements GraphQLQueryResolver {

    private WadService service;

    public WadQueryResolver(WadService service) {
        super();
        this.service = service;
    }

    public List<Wad> getWads() {
        log.info("Retrieving all wads");
        List<Wad> wads = this.service.findAll();

        log.info("Returned wads: {}", wads);
        return wads;

    }

}
