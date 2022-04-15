package com.udemy.springgraphql.service;

import com.udemy.springgraphql.graphql.type.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<Review> findReviewsByWadId(UUID wadId, int count);

}
