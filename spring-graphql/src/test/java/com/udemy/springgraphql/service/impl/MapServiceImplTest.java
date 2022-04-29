package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.service.MapService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@ExtendWith(SpringExtension.class)
public class MapServiceImplTest {

    @Autowired
    private MapService service;

    @Test
    public void givenIdWad_whenFindAllMapsByWad_shouldRetrieveList() {
        List<Map> maps = this.service.findAll(UUID.fromString("a15d5780-b37d-11ec-b909-0242ac120002"));

        assertFalse(maps.isEmpty());
    }

    @Test
    public void givenIdReview_whenFindAllByReviewIdAsMap_shouldRetrieveMap() {
        final var idReview = UUID.fromString("3720b503-4431-4d21-98f5-64528f3fc80c");
        Set<UUID> idReviews = Set.of(idReview, UUID.fromString("df420d16-dc46-4851-b532-6223ee12df8b"));

        java.util.Map<UUID, Map> maps = this.service.findAllByReviewIdAsMap(idReviews);

        assertFalse(maps.isEmpty());
        assertThat(maps.get(idReview), is(notNullValue()));
        assertThat(maps.get(idReview).getName(), is("Ritual Site"));
    }
}
