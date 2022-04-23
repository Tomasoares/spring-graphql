package com.udemy.springgraphql.jpa.mapper;

import com.udemy.springgraphql.jpa.model.Review;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReviewMapperTest {

    private OffsetDateTime sampleDateTime = OffsetDateTime.now();

    @Test
    public void givenJPAReviewWithReleasedDate_whenToGraphQL_shouldMapField() {
        Review review = buildSample();
        com.udemy.springgraphql.graphql.type.Review gQL = ReviewMapper.toGraphQL(review);
        assertThat(gQL.getPublished(), is(sampleDateTime));
    }

    private Review buildSample() {
        return Review.builder()
                .published(sampleDateTime)
                .build();
    }

}
