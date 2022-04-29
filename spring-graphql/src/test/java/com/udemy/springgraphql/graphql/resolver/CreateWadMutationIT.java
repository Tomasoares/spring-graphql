package com.udemy.springgraphql.graphql.resolver;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.jpa.model.Wad;
import com.udemy.springgraphql.jpa.repository.WadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class CreateWadMutationIT {

    private static final String NAME_BLANK_VALIDATION = "Name cannot be blank!";
    private static final String GENRE_SIZE_VALIDATION = "Genre cannot be smaller than 3 or bigger than 50";

    @Autowired
    private GraphQLTestTemplate template;

    @Autowired
    private WadRepository repository;

    @Test
    public void givenWad_whenCreateWadMutation_itShouldReturnWadsResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/createWad-mutation.graphqls");
        assertTrue(response.isOk());

        String id = response.get("$.data.createWad");
        assertNotNull(id);
    }

    @Test
    public void givenWad_whenCreateWadMutation_itShouldFillDownloadLinkField() throws Exception {
        GraphQLResponse response = template.postForResource("request/createWad-mutation.graphqls");
        assertTrue(response.isOk());

        Optional<Wad> found = repository.findById(UUID.fromString(response.get("$.data.createWad")));
        assertTrue(found.isPresent());
        assertThat(found.get().getDownloadLink(), is(notNullValue()));
    }

    @Test
    public void givenWadWithBlankName_whenCreateWadMutation_itShouldReturnValidationMessageError() throws Exception {
        GraphQLResponse response = template.postForResource("request/createWad-mutation-nameBlank.graphqls");
        assertTrue(response.isOk());

        String message = response.get("$.errors[0].message");
        assertThat(message, is(NAME_BLANK_VALIDATION));
    }

    @Test
    public void givenWadWithGenreTooSmall_whenCreateWadMutation_itShouldReturnValidationMessageError() throws Exception {
        GraphQLResponse response = template.postForResource("request/createWad-mutation-genreTooSmall.graphqls");
        assertTrue(response.isOk());

        String message = response.get("$.errors[0].message");
        assertThat(message, is(GENRE_SIZE_VALIDATION));
    }

    @Test
    public void givenWadWithGenreTooBig_whenCreateWadMutation_itShouldReturnValidationMessageError() throws Exception {
        GraphQLResponse response = template.postForResource("request/createWad-mutation-genreTooBig.graphqls");
        assertTrue(response.isOk());

        String message = response.get("$.errors[0].message");
        assertThat(message, is(GENRE_SIZE_VALIDATION));
    }
}
