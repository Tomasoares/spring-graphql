package com.udemy.springgraphql.graphql.config.dataloader;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.service.MapService;
import com.udemy.springgraphql.service.WadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@AllArgsConstructor
@Slf4j
public class DataLoaderRegistryFactory {

    public static final String WAD_BY_REVIEW = "WAD";
    public static final String MAP_BY_REVIEW = "MAP";

    private static final Executor threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final WadService wadService;
    private final MapService mapService;

    public DataLoaderRegistry build() {
        log.info("Building all data loaders");
        DataLoaderRegistry registry = new DataLoaderRegistry();
        
        registry.register(WAD_BY_REVIEW, createWadByReviewDataLoader());
        registry.register(MAP_BY_REVIEW, createMapByReviewDataLoader());

        return registry;
    }

    private DataLoader<UUID, Wad> createWadByReviewDataLoader() {
        log.info("Creating Wad data loader");

        return DataLoader.newMappedDataLoader((Set<UUID> ids) -> {
            return CompletableFuture.supplyAsync(() -> wadService.findAllByReviewIdAsMap(ids), threadPool);
        });
    }

    private DataLoader<?,?> createMapByReviewDataLoader() {
        log.info("Creating Map data loader");

        return DataLoader.newMappedDataLoader((Set<UUID> ids) -> {
            return CompletableFuture.supplyAsync(() -> mapService.findAllByReviewIdAsMap(ids), threadPool);
        });
    }
}
