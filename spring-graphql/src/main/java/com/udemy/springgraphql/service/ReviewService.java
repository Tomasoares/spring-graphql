package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Review;
import com.udemy.springgraphql.graphql.type.ReviewInput;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<Review> findReviewsByWadId(final UUID wadId, final int count);

    List<Review> findReviewsByMapId(final UUID mapId, final int count);

    List<Review> findAll(final Integer offset, final Integer size);

    UUID create(final ReviewInput review);
}
