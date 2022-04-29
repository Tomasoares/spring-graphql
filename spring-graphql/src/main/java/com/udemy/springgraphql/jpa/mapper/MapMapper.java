package com.udemy.springgraphql.jpa.mapper;

import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.jpa.model.Wad;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MapMapper {

    public static java.util.Map<UUID, Map> convertMap(List<com.udemy.springgraphql.jpa.model.Map> maps) {
        return maps.stream()
                .map(MapMapper::toGraphQLMap)
                .collect(Collectors.toMap(Map::getId, m -> m));
    }

    public static java.util.Map<UUID, Map> convertMapByReviewId(List<com.udemy.springgraphql.jpa.model.Review> reviews) {
        return reviews.stream()
                .map(r -> Pair.of(r.getId(), toGraphQLMap(r.getMap())))
                .collect(Collectors.toMap(p -> p.getFirst(), p -> p.getSecond()));
    }

    public static com.udemy.springgraphql.jpa.model.Map toJPAMap(final MapInput input) {
        final var wad = Wad.builder().id(input.getWadId()).build();

        return com.udemy.springgraphql.jpa.model.Map.builder()
                .author(input.getAuthor())
                .name(input.getName())
                .enemies(input.getEnemies())
                .partime(input.getPartime())
                .wad(wad)
                .build();
    }

    public static Map toGraphQLMap(final com.udemy.springgraphql.jpa.model.Map map) {
        return Map.builder()
                .id(map.getId())
                .author(map.getAuthor())
                .name(map.getName())
                .enemies(map.getEnemies())
                .partime(map.getPartime())
                .build();
    }
}
