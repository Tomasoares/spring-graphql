package com.udemy.springgraphql.graphql.resolvers.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.MapService;
import com.udemy.springgraphql.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
@RequiredArgsConstructor
public class WadFieldResolver implements GraphQLResolver<Wad> {

    private final MapService mapService;
    private final ReviewService reviewService;

    public List<Map> maps(Wad wad) {
        log.info("Retrieving maps from wad {}", wad.getId());
        List<Map> maps = this.mapService.findAll(wad.getId());

        log.info("Maps retrieved: {}", maps);
        return maps;
    }

    public Integer mapCount(Wad wad) {
        log.info("Counting number of maps of wad: {}", wad.getId());
        return this.mapService.getMapCountByWadId(wad);
    }

    public List<Review> reviews(Wad wad, Integer count) {
        log.info("finding {} reviews from wad id {}", wad.getId(), count);
        List<Review> reviews = this.reviewService.findReviewsByWadId(wad.getId(), count);

        log.info("Retrieved the following reviews: {}", reviews);
        return reviews;
    }
}
