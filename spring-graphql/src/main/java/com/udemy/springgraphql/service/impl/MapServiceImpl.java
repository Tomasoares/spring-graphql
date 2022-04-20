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
    public List<Map> findAll(UUID idWad) {
        List<com.udemy.springgraphql.jpa.model.Map> maps = repository.findByWadId(idWad);

        return maps.stream()
                .map(this::toGraphQLMap)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createMap(MapInput input) {
        com.udemy.springgraphql.jpa.model.Map map = toJPAMap(input);
        this.repository.saveAndFlush(map);

        return map.getId();
    }

    @Override
    public Integer getMapCountByWadId(com.udemy.springgraphql.graphql.type.Wad wad) {
        return this.repository.countByWadId(wad.getId());
    }

    @Override
    public Map findMapByReview(UUID idReview) {
        Optional<com.udemy.springgraphql.jpa.model.Map> map = this.repository.findByReviewId(idReview);

        return map.map(this::toGraphQLMap).orElse(null);
    }

    private com.udemy.springgraphql.jpa.model.Map toJPAMap(MapInput input) {
        Wad wad = Wad.builder().id(input.getWadId()).build();

        return com.udemy.springgraphql.jpa.model.Map.builder()
                .author(input.getAuthor())
                .name(input.getName())
                .enemies(input.getEnemies())
                .wad(wad)
                .build();
    }

    private Map toGraphQLMap(com.udemy.springgraphql.jpa.model.Map map) {
        return Map.builder()
                .id(map.getId())
                .author(map.getAuthor())
                .name(map.getName())
                .enemies(map.getEnemies())
                .build();
    }
}
