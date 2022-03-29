package com.udemy.springgraphql.queryresolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.udemy.springgraphql.model.Map;
import com.udemy.springgraphql.model.Wad;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class MapFieldResolver implements GraphQLResolver<Map> {

    public Wad wad(Map map) {
        return newWad();
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
