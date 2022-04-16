package com.udemy.springgraphql.graphql.resolvers.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.MapService;
import com.udemy.springgraphql.service.ReviewService;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class MapFieldResolver implements GraphQLResolver<Map> {

    private WadService wadService;

    private ReviewService reviewService;

    public MapFieldResolver(WadService wadService, ReviewService reviewService) {
        super();
        this.wadService = wadService;
        this.reviewService = reviewService;
    }

    public Wad wad(Map map) {
        log.info("Finding wad by map id: {}", map.getId());
        Wad result = this.wadService.findWadByMap(map.getId());

        log.info("Map found: {}", result);
        return result;
    }

    public List<Review> reviews(Map map, Integer count) {
        log.info("Retrieving reviews of map {}", map.getId());
        List<Review> reviews = this.reviewService.findReviewsByMapId(map.getId(), count);

        log.info("Reviews found: {}", reviews);
        return reviews;
    }

}
