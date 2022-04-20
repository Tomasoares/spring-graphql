package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.graphql.type.Wad;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MapService {

    List<Map> findAll(final UUID idWad);

    UUID createMap(final MapInput input);

    Integer getMapCountByWadId(final Wad wad);

    Map findMapByReview(final UUID id);
    
}
