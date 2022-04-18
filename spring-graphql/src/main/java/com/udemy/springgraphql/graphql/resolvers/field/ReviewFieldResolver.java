package com.udemy.springgraphql.graphql.resolvers.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.MapService;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReviewFieldResolver implements GraphQLResolver<Review> {

    private WadService wadService;

    private MapService mapService;

    public ReviewFieldResolver(WadService wadService, MapService mapService) {
        super();
        this.wadService = wadService;
        this.mapService = mapService;
    }

    public Wad getWad(Review review) {
        log.info("Finding wad of review: {}", review);
        Wad wad = this.wadService.findWadByReview(review.getId());

        log.info("Found the following wad: {}", wad);
        return wad;
    }

    public Map getMap(Review review) {
        log.info("Finding map of review: {}", review);

        Map map = this.mapService.findMapByReview(review.getId());

        log.info("Found the following map: {}", map);
        return map;
    }
}
