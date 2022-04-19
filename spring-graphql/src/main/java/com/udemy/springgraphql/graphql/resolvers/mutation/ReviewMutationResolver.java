package com.udemy.springgraphql.graphql.resolvers.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udemy.springgraphql.graphql.type.ReviewInput;
import com.udemy.springgraphql.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ReviewMutationResolver implements GraphQLMutationResolver {

    private ReviewService service;

    public ReviewMutationResolver(ReviewService service) {
        super();
        this.service = service;
    }

    public UUID createReview(ReviewInput review) {
        log.info("Creating new wad: {}", review);
        UUID id = this.service.create(review);

        log.info("Generated id: {}", id);
        return id;
    }

}
