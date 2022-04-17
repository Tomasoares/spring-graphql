package com.udemy.springgraphql.graphql.resolvers.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ReviewQueryResolver implements GraphQLQueryResolver {

    private ReviewService reviewService;

    public ReviewQueryResolver(ReviewService reviewService) {
        super();
        this.reviewService = reviewService;
    }

    public List<Review> reviews(Integer count, Integer page) {
        log.info("Finding all reviews from page {} of size {}", page, count);
        List<Review> reviews = this.reviewService.findAll(page, count);

        log.info("Found reviews: {}", reviews);
        return reviews;
    }
}
