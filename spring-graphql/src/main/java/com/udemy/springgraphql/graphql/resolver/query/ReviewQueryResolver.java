package com.udemy.springgraphql.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewQueryResolver implements GraphQLQueryResolver {

    private final ReviewService reviewService;

    public List<Review> reviews(final Integer count, final Integer page) {
        log.info("Finding all reviews from page {} of size {}", page, count);
        final var reviews = this.reviewService.findAll(page, count);

        log.info("Found reviews: {}", reviews);
        return reviews;
    }
}
