package com.udemy.springgraphql.graphql.resolvers.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.udemy.springgraphql.graphql.type.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final ReviewPublisher publisher;

    public Publisher<Review> listenNewReviewsByWad(UUID wadId) {
        log.info("listening to reviews of wad id {}", wadId);
        return this.publisher.retrieveReviewByWadId(wadId);
    }
}
