package com.udemy.springgraphql.jpa.mapper;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.graphql.type.WadInput;

import java.util.List;
import java.util.stream.Collectors;

public class WadMapper {

    public static List<Wad> convertList(final List<com.udemy.springgraphql.jpa.model.Wad> fromDb) {
        return fromDb
                .stream()
                .map(WadMapper::toGraphQLWad)
                .collect(Collectors.toList());
    }

    public static Wad toGraphQLWad(final com.udemy.springgraphql.jpa.model.Wad wad) {
        return Wad.builder()
                .id(wad.getId())
                .name(wad.getName())
                .genre(wad.getGenre())
                .iwad(wad.getIwad())
                .released(wad.getReleased())
                .build();
    }

    public static com.udemy.springgraphql.jpa.model.Wad toJPA(final WadInput input) {
        return com.udemy.springgraphql.jpa.model.Wad.builder()
                .name(input.getName())
                .genre(input.getGenre())
                .iwad(input.getIwad())
                .released(input.getReleased())
                .build();
    }
}
