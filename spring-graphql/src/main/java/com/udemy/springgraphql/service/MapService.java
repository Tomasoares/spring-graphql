package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.MapInput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface MapService {

    List<Map> findAll(UUID idWad);

    UUID createMap(MapInput input);

}
