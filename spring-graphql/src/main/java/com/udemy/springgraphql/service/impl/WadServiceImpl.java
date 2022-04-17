package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.graphql.type.WadInput;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import com.udemy.springgraphql.service.WadService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        //TODO: use optional
        com.udemy.springgraphql.jpa.model.Wad wad = repository.findByMapId(idMap);
        return toGraphQLWad(wad);
    }

    @Override
    public List<Wad> findAll() {
        return convertList(repository.findAll());
    }

    @Override
    public List<Wad> getCacowards(int count, int page) {
        Pageable repositoryPage = PageRequest.of(page, count);
        return convertList(repository.findAll(repositoryPage).toList());
    }

    @Override
    public UUID createWad(WadInput input) {
        com.udemy.springgraphql.jpa.model.Wad jpa = toJPA(input);
        this.repository.saveAndFlush(jpa);
        return jpa.getId();
    }

    @Override
    public Wad findWadByReview(UUID reviewId) {
        com.udemy.springgraphql.jpa.model.Wad found = this.repository.findByReviewId(reviewId);
        return toGraphQLWad(found);
    }

    private List<Wad> convertList(List<com.udemy.springgraphql.jpa.model.Wad> fromDb) {
        return fromDb
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

    private com.udemy.springgraphql.jpa.model.Wad toJPA(WadInput input) {
        return com.udemy.springgraphql.jpa.model.Wad.builder()
                .name(input.getName())
                .genre(input.getGenre())
                .iwad(input.getIwad())
                .build();
    }
}
