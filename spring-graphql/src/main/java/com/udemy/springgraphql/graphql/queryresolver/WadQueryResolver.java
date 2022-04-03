package com.udemy.springgraphql.graphql.queryresolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udemy.springgraphql.graphql.type.Wad;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class WadQueryResolver implements GraphQLQueryResolver {

    public List<Wad> getWads() {
        return List.of(newWad());
    }

    private Wad newWad() {
        return Wad.builder()
                .id(UUID.randomUUID())
                .name("Ancient Aliens")
                .iwad("Doom 2")
                .genre("Slaughter Lite")
                .build();
    }

}
