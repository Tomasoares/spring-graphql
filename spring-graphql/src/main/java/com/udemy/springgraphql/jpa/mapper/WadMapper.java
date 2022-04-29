package com.udemy.springgraphql.jpa.mapper;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.graphql.type.WadInput;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class WadMapper {

    public static java.util.Map<UUID, com.udemy.springgraphql.graphql.type.Wad> convertMapByWadId(List<com.udemy.springgraphql.jpa.model.Review> reviews) {
        return reviews.stream()
                .map(r -> Pair.of(r.getId(), toGraphQLWad(r.getWad())))
                .collect(Collectors.toMap(p -> p.getFirst(), p -> p.getSecond()));
    }

    public static Map<UUID, Wad> convertMap(final List<com.udemy.springgraphql.jpa.model.Wad> fromDb) {
        return fromDb
                .stream()
                .map(WadMapper::toGraphQLWad)
                .collect(Collectors.toMap(Wad::getId, w -> w));
    }

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
                .downloadLink(wad.getDownloadLink())
                .build();
    }

    public static com.udemy.springgraphql.jpa.model.Wad toJPA(final WadInput input) {
        return com.udemy.springgraphql.jpa.model.Wad.builder()
                .name(input.getName())
                .genre(input.getGenre())
                .iwad(input.getIwad())
                .released(input.getReleased())
                .downloadLink(input.getDownloadLink())
                .build();
    }
}
