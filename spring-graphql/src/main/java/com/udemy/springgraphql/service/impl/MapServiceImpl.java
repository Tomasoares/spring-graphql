package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.exception.ResourceNotFoundException;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.graphql.type.MapInput;
import com.udemy.springgraphql.jpa.mapper.MapMapper;
import com.udemy.springgraphql.jpa.repository.MapRepository;
import com.udemy.springgraphql.jpa.repository.ReviewRepository;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import com.udemy.springgraphql.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.udemy.springgraphql.jpa.mapper.MapMapper.convertMapByReviewId;
import static com.udemy.springgraphql.jpa.mapper.MapMapper.toJPAMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapServiceImpl implements MapService {

    private final MapRepository repository;
    private final WadRepository wadRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<Map> findAll(final UUID idWad) {
        final var maps = repository.findByWadId(idWad);

        return maps.stream()
                .map(MapMapper::toGraphQLMap)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createMap(final MapInput input) {
        if (!this.wadRepository.existsById(input.getWadId())) {
            throw new ResourceNotFoundException("No wad found with id " + input.getWadId());
        }

        final var map = toJPAMap(input);
        this.repository.saveAndFlush(map);

        return map.getId();
    }

    @Override
    public Integer getMapCountByWadId(final com.udemy.springgraphql.graphql.type.Wad wad) {
        return this.repository.countByWadId(wad.getId());
    }

    @Override
    public Map findMapByReview(final UUID idReview) {
        final var map = this.repository.findByReviewId(idReview);
        return map.map(MapMapper::toGraphQLMap).orElse(null);
    }

    @Override
    public java.util.Map<UUID, Map> findAllByReviewIdAsMap(Set<UUID> ids) {
        log.info("Starting query...");
        List<com.udemy.springgraphql.jpa.model.Review> result = this.reviewRepository.findAllByIdPlusMaps(ids);
        log.info("Ending query...");

        return convertMapByReviewId(result);
    }
}
