package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.jpa.repository.MapRepository;
import com.udemy.springgraphql.service.MapService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    private final MapRepository repository;

    public MapServiceImpl(MapRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<Map> findAll(UUID idWad) {
        List<com.udemy.springgraphql.jpa.model.Map> maps = repository.findByWadId(idWad);

        return maps.stream()
                .map(this::toGraphQLMap)
                .collect(Collectors.toList());
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
