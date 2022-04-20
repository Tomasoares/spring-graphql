package com.udemy.springgraphql.graphql.resolvers.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udemy.springgraphql.graphql.type.ReviewInput;
import com.udemy.springgraphql.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewMutationResolver implements GraphQLMutationResolver {

    private final ReviewService service;

    public UUID createReview(final ReviewInput review) {
        log.info("Creating new wad: {}", review);
        final var id = this.service.create(review);

        log.info("Generated id: {}", id);
        return id;
    }

}
