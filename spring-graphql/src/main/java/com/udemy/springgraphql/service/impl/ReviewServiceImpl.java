package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.exception.ResourceNotFoundException;
import com.udemy.springgraphql.graphql.resolvers.subscription.ReviewPublisher;
import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.ReviewInput;
import com.udemy.springgraphql.jpa.model.Map;
import com.udemy.springgraphql.jpa.model.Wad;
import com.udemy.springgraphql.jpa.repository.MapRepository;
import com.udemy.springgraphql.jpa.repository.ReviewRepository;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import com.udemy.springgraphql.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final MapRepository mapRepository;
    private final WadRepository wadRepository;

    private final ReviewPublisher publisher;

    @Override
    public List<Review> findReviewsByWadId(final UUID wadId, final int count) {
        final var page = PageRequest.of(0, count);
        var reviews = this.repository.findAllByWadId(wadId, page);

        return convertList(reviews);
    }

    @Override
    public List<Review> findReviewsByMapId(final UUID mapId, final int count) {
        final var page = PageRequest.of(0, count);
        final var reviews = this.repository.findAllByMapId(mapId, page);

        return convertList(reviews);
    }

    @Override
    public List<Review> findAll(final Integer offset, final Integer size) {
        final var page = PageRequest.of(offset, size);
        final var reviews = this.repository.findAll(page);

        return convertList(reviews.toList());
    }

    @Override
    public UUID create(final ReviewInput review) {
        if (!Objects.isNull(review.getMapId()) && !mapRepository.existsById(review.getMapId())) {
            throw new ResourceNotFoundException("No map found with id " + review.getMapId());
        }

        if (!Objects.isNull(review.getWadId()) && !wadRepository.existsById(review.getWadId())) {
            throw new ResourceNotFoundException("No Wad found with id " + review.getWadId());
        }

        final com.udemy.springgraphql.jpa.model.Review save = this.repository.save(toJPA(review));

        log.info("Publishing saved review {] to subscribe", save.getId());
        this.publisher.publish(toGraphQL(save), save.getWad().getId());

        return save.getId();
    }

    private com.udemy.springgraphql.jpa.model.Review toJPA(final ReviewInput review) {
        return com.udemy.springgraphql.jpa.model.Review.builder()
                .description(review.getDescription())
                .author(review.getAuthor())
                .rating(review.getRating())
                .wad(!Objects.isNull(review.getWadId())? Wad.builder().id(review.getWadId()).build() : null)
                .map(!Objects.isNull(review.getMapId())? Map.builder().id(review.getMapId()).build() : null)
                .build();
    }

    private List<Review> convertList(final List<com.udemy.springgraphql.jpa.model.Review> reviews) {
        return reviews.stream()
                .map(this::toGraphQL)
                .collect(Collectors.toList());
    }

    private Review toGraphQL(final com.udemy.springgraphql.jpa.model.Review review) {
        return Review.builder()
                .id(review.getId())
                .author(review.getAuthor())
                .description(review.getDescription())
                .rating(review.getRating())
                .build();
    }

}
