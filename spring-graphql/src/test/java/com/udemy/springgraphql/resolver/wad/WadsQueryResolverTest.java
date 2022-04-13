package com.udemy.springgraphql.resolver.wad;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.util.JsonReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class WadsQueryResolverTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void givenWadsQuery_whenWadsQuery_itShouldReturnWadsResponse() throws Exception {
        GraphQLResponse response = graphQLTestTemplate.postForResource("request/wads-query.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/wads-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);

    }

}