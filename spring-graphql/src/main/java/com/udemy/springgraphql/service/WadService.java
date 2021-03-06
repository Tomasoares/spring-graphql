package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.graphql.type.WadInput;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface WadService {

    Wad findWadByMap(final UUID id);

    List<Wad> findAll();

    List<Wad> findAll(List<UUID> ids);

    List<Wad> getCacowards(final int count, final int page);

    UUID createWad(final WadInput input);

    Wad findWadByReview(final UUID id);

    Map<UUID, Wad> findAllAsMap(Set<UUID> ids);

    Map<UUID, Wad> findAllByReviewIdAsMap(Set<UUID> ids);
}
