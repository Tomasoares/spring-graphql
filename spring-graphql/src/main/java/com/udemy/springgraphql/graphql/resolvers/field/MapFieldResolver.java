package com.udemy.springgraphql.graphql.resolvers.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
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

    public List<Review> reviews(Map map, Integer count) {
        log.info("Retrieving reviews of map {}", map.getId());
        return List.of(Review.builder().id(UUID.randomUUID()).build());
    }

}
