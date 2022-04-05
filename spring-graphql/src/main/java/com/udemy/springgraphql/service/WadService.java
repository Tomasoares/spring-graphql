package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Wad;

import java.util.List;
import java.util.UUID;

public interface WadService {

    Wad findWadByMap(UUID id);

    List<Wad> findAll();

    List<Wad> getCacowards(int count, int page);
    
}
