package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.jpa.model.Wad;
import com.udemy.springgraphql.jpa.repository.MapRepository;
import com.udemy.springgraphql.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapRepository repository;

    @Override
    public List<Map> findAll(final UUID idWad) {
        final var maps = repository.findByWadId(idWad);

        return maps.stream()
                .map(this::toGraphQLMap)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createMap(final MapInput input) {
        final var map = toJPAMap(input);
        this.repository.saveAndFlush(map);

        return map.getId();
    }

    @Override
    public Integer getMapCountByWadId(final com.udemy.springgraphql.graphql.type.Wad wad) {
        return this.repository.countByWadId(wad.getId());
    }

    @Override
    public Map findMapByReview(final UUID idReview) {
        final var map = this.repository.findByReviewId(idReview);

        return map.map(this::toGraphQLMap).orElse(null);
    }

    private com.udemy.springgraphql.jpa.model.Map toJPAMap(final MapInput input) {
        final var wad = Wad.builder().id(input.getWadId()).build();

        return com.udemy.springgraphql.jpa.model.Map.builder()
                .author(input.getAuthor())
                .name(input.getName())
                .enemies(input.getEnemies())
                .wad(wad)
                .build();
    }

    private Map toGraphQLMap(final com.udemy.springgraphql.jpa.model.Map map) {
        return Map.builder()
                .id(map.getId())
                .author(map.getAuthor())
                .name(map.getName())
                .enemies(map.getEnemies())
                .build();
    }
}
