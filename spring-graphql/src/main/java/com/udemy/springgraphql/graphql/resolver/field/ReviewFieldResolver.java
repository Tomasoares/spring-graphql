package com.udemy.springgraphql.graphql.resolver.field;

import com.udemy.springgraphql.graphql.dataloader.DataLoaderRegistryFactory;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.MapService;
import com.udemy.springgraphql.service.WadService;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewFieldResolver implements GraphQLResolver<Review> {

    private final WadService wadService;
    private final MapService mapService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public CompletableFuture<Wad> getWad(final Review review) {
        log.info("Finding wad of review: {}", review);
        return CompletableFuture.supplyAsync(() -> findWadById(review));
    }

    private Wad findWadById(Review review) {
        final var wad = this.wadService.findWadByReview(review.getId());
        log.info("Found the following wad: {}", wad);

        return wad;
    }

    public CompletableFuture<Map> getMap(final Review review, final DataFetchingEnvironment env) {
        log.info("Finding map of review: {}", review);
        String idMap = env.getArgument("id");

        DataLoader<UUID, Map> dataLoader = env.getDataLoader(DataLoaderRegistryFactory.MAP_BY_REVIEW);

        return dataLoader.load(review.getId());
    }
}
