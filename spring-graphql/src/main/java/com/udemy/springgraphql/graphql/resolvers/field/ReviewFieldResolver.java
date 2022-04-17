package com.udemy.springgraphql.graphql.resolvers.field;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.ReviewService;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ReviewFieldResolver implements GraphQLResolver<Review> {

    private WadService service;

    public ReviewFieldResolver(WadService service) {
        super();
        this.service = service;
    }

    public Wad getWad(Review review) {
        log.info("Finding wad of review: {}", review);
        Wad wad = this.service.findWadByReview(review.getId());

        log.info("Found the following wad: {}", wad);
        return wad;
    }
}
