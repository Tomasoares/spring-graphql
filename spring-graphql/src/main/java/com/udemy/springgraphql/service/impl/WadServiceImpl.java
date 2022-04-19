package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.resolvers.subscription.WadPublisher;
import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.graphql.type.WadInput;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import com.udemy.springgraphql.service.WadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WadServiceImpl implements WadService {

    private final WadRepository repository;

    private final WadPublisher publisher;

    public WadServiceImpl(WadRepository repository, WadPublisher publisher) {
        super();
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override
    public Wad findWadByMap(UUID idMap) {
        //TODO: use optional
        Optional<com.udemy.springgraphql.jpa.model.Wad> wad = repository.findByMapId(idMap);
        return wad.map(this::toGraphQLWad).orElse(null);
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

        log.info("Adding new post {} to publisher", jpa);
        this.publisher.pushWad(toGraphQLWad(jpa));

        return jpa.getId();
    }

    @Override
    public Wad findWadByReview(UUID reviewId) {
        Optional<com.udemy.springgraphql.jpa.model.Wad> found = this.repository.findByReviewId(reviewId);
        return found.map(this::toGraphQLWad).orElse(null);
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
