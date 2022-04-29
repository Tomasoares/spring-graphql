package com.udemy.springgraphql.graphql.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class WadQueryIT {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    public void givenWadQuery_whenWadQuery_itShouldReturnWadResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/wad-query.graphqls");
        assertThat("response should be ok", response.isOk());

        String id = response.get("$.data.wad.id");
        String name = response.get("$.data.wad.name");

        assertThat(id, is(notNullValue()));
        assertThat(name, is("Valiant"));
    }
}
