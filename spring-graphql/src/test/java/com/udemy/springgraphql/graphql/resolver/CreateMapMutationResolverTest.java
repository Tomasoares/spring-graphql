package com.udemy.springgraphql.graphql.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.jpa.model.Map;
import com.udemy.springgraphql.jpa.repository.MapRepository;
import com.udemy.springgraphql.service.MapService;
import com.udemy.springgraphql.service.impl.MapServiceImpl;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class CreateMapMutationResolverTest {

    @Autowired
    private GraphQLTestTemplate template;

    private static final String NEGATIVE_ENEMIES_VALIDATION = "Number of enemies must be greater or equal to 0";

    @Test
    public void givenMapInput_whenCreateMapMutation_itShouldReturnIdResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/createMap-mutation.graphqls");
        assertThat(response.isOk());

        String id = response.get("$.data.createMap");
        assertNotNull(id);
    }

    @Test
    public void givenMapInputWithPartime_whenCreateMapMutation_itShouldSaveInMapEntity() throws Exception {
        GraphQLResponse response = template.postForResource("request/createMap-mutation-partime.graphqls");
        assertThat(response.isOk());

        String id = response.get("$.data.createMap");
        assertNotNull(id);
    }

    @Test
    public void givenMapInputWithNegativeEnemies_whenCreateMapMutation_itShouldPresentValidationError() throws Exception {
        GraphQLResponse response = template.postForResource("request/createMap-mutation-negativeEnemies.graphqls");
        assertThat(response.isOk());

        String message = response.get("$.errors[0].message");
        MatcherAssert.assertThat(message, is(NEGATIVE_ENEMIES_VALIDATION));
    }
}
