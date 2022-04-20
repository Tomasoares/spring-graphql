package com.udemy.springgraphql.graphql.resolvers.subscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.udemy.springgraphql.graphql.type.Wad;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class WadSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final WadPublisher publisher;

    public Publisher<Wad> listenNewWads() {
        log.info("Creating wad subscription");
        return publisher.retrieveWad();
    }

    public Publisher<Wad> listenNewWadsByIwad(String iwad) {
        log.info("Creating wad subscription with iwad: {}", iwad);
        return publisher.retrieveWadByIwad(iwad);
    }

}
