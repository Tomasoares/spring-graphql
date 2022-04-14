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
public class WadFieldResolverTest {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    public void givenWadTypeRequested_whenRequestingMapCountField_shouldReturnCount() throws Exception {
        GraphQLResponse response = template.postForResource("request/wads-postCount-query.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/wads-postCount-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);
    }

}
