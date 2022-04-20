package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Wad;
import com.udemy.springgraphql.graphql.type.WadInput;

import java.util.List;
import java.util.UUID;

public interface WadService {

    Wad findWadByMap(final UUID id);

    List<Wad> findAll();

    List<Wad> getCacowards(final int count, final int page);

    UUID createWad(final WadInput input);

    Wad findWadByReview(final UUID id);

}
