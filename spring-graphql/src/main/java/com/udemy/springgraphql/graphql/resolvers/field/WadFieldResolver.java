package com.udemy.springgraphql.graphql.resolvers.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.MapService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
public class WadFieldResolver implements GraphQLResolver<Wad> {

    private MapService service;

    public WadFieldResolver(MapService service) {
        super();
        this.service = service;
    }

    public List<Map> maps(Wad wad) {
        log.info("Retrieving maps from wad {}", wad.getId());
        List<Map> maps = this.service.findAll(wad.getId());

        log.info("Maps retrieved: {}", maps);
        return maps;
    }

    public Integer mapCount(Wad wad) {
        log.info("Counting number of maps of wad: {}", wad.getId());
        return this.service.getMapCountByWadId(wad);
    }
}
