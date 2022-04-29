package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.graphql.type.Wad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@ExtendWith(SpringExtension.class)
public class WadServiceImplTest {

    @Autowired
    private WadServiceImpl service;

    @Test
    public void givenIdReview_whenFindAllByReviewIdAsMap_shouldRetrieveMap() {
        final var idReview = UUID.fromString("bb8f079f-182a-42b5-942d-818a5eb0f1fc");
        Set<UUID> idReviews = Set.of(idReview, UUID.fromString("ff044f08-9235-410f-8ef7-a272bb48d90e"));

        java.util.Map<UUID, Wad> wads = this.service.findAllByReviewIdAsMap(idReviews);

        assertFalse(wads.isEmpty());
        assertThat(wads.get(idReview), is(notNullValue()));
        assertThat(wads.get(idReview).getName(), is("Valiant"));
    }
}
