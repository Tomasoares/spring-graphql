package com.udemy.springgraphql.jpa.mapper;

import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.ReviewInput;
import com.udemy.springgraphql.jpa.model.Map;
import com.udemy.springgraphql.jpa.model.Wad;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReviewMapper {

    public static com.udemy.springgraphql.jpa.model.Review toJPA(final ReviewInput review) {
        return com.udemy.springgraphql.jpa.model.Review.builder()
                .description(review.getDescription())
                .author(review.getAuthor())
                .rating(review.getRating())
                .wad(!Objects.isNull(review.getWadId())? Wad.builder().id(review.getWadId()).build() : null)
                .map(!Objects.isNull(review.getMapId())? Map.builder().id(review.getMapId()).build() : null)
                .build();
    }

    public static List<Review> convertList(final List<com.udemy.springgraphql.jpa.model.Review> reviews) {
        return reviews.stream()
                .map(this::toGraphQL)
                .collect(Collectors.toList());
    }

    public static Review toGraphQL(final com.udemy.springgraphql.jpa.model.Review review) {
        return Review.builder()
                .id(review.getId())
                .author(review.getAuthor())
                .description(review.getDescription())
                .rating(review.getRating())
                .build();
    }
}
