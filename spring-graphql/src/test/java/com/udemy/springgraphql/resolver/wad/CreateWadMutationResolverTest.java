package com.udemy.springgraphql.resolver.wad;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.util.JsonReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class CreateWadMutationResolverTest {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    public void givenWadsQuery_whenWadsQuery_itShouldReturnWadsResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/createWad-mutation.graphqls");
        assertThat(response.isOk());

        String id = response.get("$.data.createWad");
        assertNotNull(id);
    }
}
