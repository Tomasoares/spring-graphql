package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.jpa.repository.ReviewRepository;
import com.udemy.springgraphql.service.ReviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<Review> findReviewsByWadId(UUID wadId, int count) {
        PageRequest page = PageRequest.of(0, count);
        List<com.udemy.springgraphql.jpa.model.Review> reviews = this.repository.findAllByWadId(wadId, page);

        return covertList(reviews);
    }

    @Override
    public List<Review> findReviewsByMapId(UUID mapId, int count) {
        PageRequest page = PageRequest.of(0, count);
        List<com.udemy.springgraphql.jpa.model.Review> reviews = this.repository.findAllByMapId(mapId, page);

        return covertList(reviews);
    }

    private List<Review> covertList(List<com.udemy.springgraphql.jpa.model.Review> reviews) {
        return reviews.stream()
                .map(this::toGraphQL)
                .collect(Collectors.toList());
    }

    private Review toGraphQL(com.udemy.springgraphql.jpa.model.Review review) {
        return Review.builder()
                .id(review.getId())
                .author(review.getAuthor())
                .description(review.getDescription())
                .rating(review.getRating())
                .build();
    }

}
