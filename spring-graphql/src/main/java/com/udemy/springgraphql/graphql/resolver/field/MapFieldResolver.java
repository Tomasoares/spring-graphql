package com.udemy.springgraphql.graphql.resolver.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.ReviewService;
import com.udemy.springgraphql.service.WadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class MapFieldResolver implements GraphQLResolver<Map> {

    private final WadService wadService;
    private final ReviewService reviewService;

    public Wad wad(final Map map) {
        log.info("Finding wad by map id: {}", map.getId());
        final var result = this.wadService.findWadByMap(map.getId());

        log.info("Map found: {}", result);
        return result;
    }

    public List<Review> reviews(final Map map, final Integer count) {
        log.info("Retrieving reviews of map {}", map.getId());
        final var reviews = this.reviewService.findReviewsByMapId(map.getId(), count);

        log.info("Reviews found: {}", reviews);
        return reviews;
    }

}
