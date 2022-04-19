package com.udemy.springgraphql.graphql.resolvers.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.udemy.springgraphql.graphql.type.Review;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ReviewSubscriptionResolver implements GraphQLSubscriptionResolver {

    private ReviewPublisher publisher;

    public ReviewSubscriptionResolver(ReviewPublisher publisher) {
        super();
        this.publisher = publisher;
    }

    public Publisher<Review> listenNewReviewsByWad(UUID wadId) {
        log.info("listening to reviews of wad id {}", wadId);
        return this.publisher.retrieveReviewByWadId(wadId);
    }
}
