package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.ReviewInput;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<Review> findReviewsByWadId(UUID wadId, int count);

    List<Review> findReviewsByMapId(UUID mapId, int count);

    List<Review> findAll(Integer offset, Integer size);

    UUID create(ReviewInput review);
}
