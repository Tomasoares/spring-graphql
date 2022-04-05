package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import com.udemy.springgraphql.service.WadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WadServiceImpl implements WadService {

    private WadRepository repository;

    public WadServiceImpl(WadRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Wad findWadByMap(UUID idMap) {
        com.udemy.springgraphql.jpa.model.Wad wad = repository.findByMapId(idMap);
        return toGraphQLWad(wad);
    }

    @Override
    public List<Wad> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toGraphQLWad)
                .collect(Collectors.toList());
    }

    private Wad toGraphQLWad(com.udemy.springgraphql.jpa.model.Wad wad) {
        return Wad.builder()
                .id(wad.getId())
                .name(wad.getName())
                .genre(wad.getGenre())
                .iwad(wad.getIwad())
                .build();
    }
}
