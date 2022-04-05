package com.udemy.springgraphql.graphql.queryresolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Log4j2
public class MapFieldResolver implements GraphQLResolver<Map> {

    private WadService service;

    public MapFieldResolver(WadService service) {
        super();
        this.service = service;
    }

    public Wad wad(Map map) {
        log.info("Finding wad by map id: {}", map.getId());
        Wad result = this.service.findWadByMap(map.getId());

        log.info("Map found: {}", result);
        return result;
    }

}
