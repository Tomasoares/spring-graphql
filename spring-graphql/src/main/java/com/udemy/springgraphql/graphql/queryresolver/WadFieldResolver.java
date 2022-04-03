package com.udemy.springgraphql.graphql.queryresolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.Wad;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WadFieldResolver implements GraphQLResolver<Wad> {

    public List<Map> maps(Wad wad) {
        return List.of(newMap(wad));
    }

    private Map newMap(Wad wad) {
        return Map.builder()
                .id(UUID.randomUUID())
                .author("Skillsaw")
                .name("Entryway")
                .enemies(76)
                .build();
    }
}
