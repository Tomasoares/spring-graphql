package com.udemy.springgraphql.graphql.resolver.wad;

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

    @Test
    public void givenWadsQueryReleased_whenWadsQuery_itShouldReturnWadsReleasedResponse() throws Exception {
        GraphQLResponse response = graphQLTestTemplate.postForResource("request/wads-query-released.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/wads-released-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);
    }

    @Test
    public void givenWadsQueryMapPartime_whenWadsQuery_itShouldReturnWadsMapPartimeResponse() throws Exception {
        GraphQLResponse response = graphQLTestTemplate.postForResource("request/wads-query-map-partime.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/wads-map-partime-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);
    }

    @Test
    public void givenWadsQueryDownloadLink_whenWadsQuery_itShouldReturnWadsDownloadLinkResponse() throws Exception {
        GraphQLResponse response = graphQLTestTemplate.postForResource("request/wads-query-downloadLink.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/wads-downloadLink-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);
    }
}
