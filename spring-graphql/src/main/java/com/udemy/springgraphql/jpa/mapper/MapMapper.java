package com.udemy.springgraphql.jpa.mapper;

import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.jpa.model.Wad;

public class MapMapper {

    public static  com.udemy.springgraphql.jpa.model.Map toJPAMap(final MapInput input) {
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
