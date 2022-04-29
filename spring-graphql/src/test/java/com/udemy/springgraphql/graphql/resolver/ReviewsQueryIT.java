package com.udemy.springgraphql.graphql.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.util.JsonReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class ReviewsQueryIT {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    public void givenWadQuery_whenWadQuery_itShouldReturnWadResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/reviews-query.graphqls");
        assertThat("response should be ok", response.isOk());

        String read = JsonReaderUtil.read("response/reviews-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);
    }
}
