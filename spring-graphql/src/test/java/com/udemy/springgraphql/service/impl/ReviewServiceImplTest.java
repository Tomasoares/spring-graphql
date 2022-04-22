package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.graphql.resolver.subscription.ReviewPublisher;
import com.udemy.springgraphql.graphql.type.ReviewInput;
import com.udemy.springgraphql.jpa.model.Review;
import com.udemy.springgraphql.jpa.repository.MapRepository;
import com.udemy.springgraphql.jpa.repository.ReviewRepository;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class ReviewServiceImplTest {

    private ReviewServiceImpl testee;

    @Mock
    private ReviewPublisher publisher;

    @Mock
    private WadRepository wadRepository;

    @Mock
    private ReviewRepository repository;

    @Mock
    private MapRepository mapRepository;

    public ReviewServiceImplTest() {
        super();
    }

    @BeforeEach
    public void setup() {
        this.testee = new ReviewServiceImpl(repository, mapRepository, wadRepository, publisher);
    }

    @Test
    public void givenNewReview_whenCreate_shouldAddDefaultDate() {
        ReviewInput sample = buildSample();
        Review review = this.testee.prepareSave(sample);

        assertThat(review.getPublished(), is(not(nullValue())));
    }

    @Test
    public void givenReviewWithWadIdNull_whenCreate_shouldntCallReviewPublisher() {
        ReviewInput sample = buildSample();
        sample.setWadId(null);

        UUID uuid = this.testee.create(sample);

        verify(publisher, times(0)).publish(any(), any());
    }

    private ReviewInput buildSample() {
        return ReviewInput.builder()
                .author("")
                .description("")
                .rating(0)
                .build();
    }

}
