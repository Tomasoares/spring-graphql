package com.udemy.springgraphql.graphql.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.util.JsonReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class CacowardsQueryIT {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    public void givenCacowardsQuery_whenCacowardsQuery_itShouldReturnResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/cacowards-query.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/cacowards-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);

    }
}
