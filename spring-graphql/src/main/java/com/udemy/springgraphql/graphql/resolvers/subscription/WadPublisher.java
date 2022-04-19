package com.udemy.springgraphql.graphql.resolvers.subscription;

import com.udemy.springgraphql.graphql.type.Wad;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class WadPublisher {

    private final Sinks.Many<Wad> sink;

    public WadPublisher() {
        this.sink = Sinks.many().replay().all();
    }

    public Publisher<Wad> retrieveWad() {
        return sink.asFlux();
    }

    public Publisher<Wad> retrieveWadByIwad(String iwad) {
        return sink.asFlux().filter(w -> w.getIwad().equals(iwad));
    }

    public void pushWad(Wad wad) {
        sink.emitNext(wad, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
