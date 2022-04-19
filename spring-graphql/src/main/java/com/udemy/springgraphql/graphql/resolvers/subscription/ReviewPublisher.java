package com.udemy.springgraphql.graphql.resolvers.subscription;

import com.udemy.springgraphql.graphql.resolvers.subscription.util.ReviewSubscribe;
import com.udemy.springgraphql.graphql.type.Review;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class ReviewPublisher {

    private final Sinks.Many<ReviewSubscribe> sink;

    public ReviewPublisher() {
        this.sink = Sinks.many().replay().all();
    }

    public void publish(Review r, UUID wadId) {
        ReviewSubscribe subscribe = ReviewSubscribe.builder()
                .review(r)
                .wadId(wadId)
                .build();

        this.sink.emitNext(subscribe, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<Review> retrieveReviewByWadId(UUID wadId) {
        return sink.asFlux()
                .filter(r -> wadId.equals(r.getWadId()))
                .map(ReviewSubscribe::getReview);
    }
}
