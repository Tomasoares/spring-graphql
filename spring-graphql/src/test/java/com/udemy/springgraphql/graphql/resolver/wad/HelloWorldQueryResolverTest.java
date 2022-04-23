package com.udemy.springgraphql.graphql.resolver.wad;

import com.fasterxml.jackson.databind.JsonNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.util.JsonReaderUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class HelloWorldQueryResolverTest {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    public void givenHelloWorldQuery_whenHelloWorldQuery_itShouldReturnResponse() throws Exception {
        GraphQLResponse response = template.postForResource("request/helloWorld-query.graphqls");
        assertThat(response.isOk());

        String read = JsonReaderUtil.read("response/helloWorld-response.json");
        assertEquals(read, response.getRawResponse().getBody(), true);

    }

    String telephoneQuery = "query($phone: PhoneNumber) {helloWorld(phoneNumber: $phone)}";

    @Test
    public void givenCorrectTelephoneQuery_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("(48)99114-6675");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertTrue(isErrorsEmpty(response));
    }

    @Test
    public void givenCorrectTelephone2Query_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("(55)98426-8843");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertTrue(isErrorsEmpty(response));
    }

    @Test
    public void givenIncorrectTelephoneQuery_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("(482)99114-6675");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertFalse(isErrorsEmpty(response));
    }

    @Test
    public void givenIncorrectTelephone2Query_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("48 99114-6675");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertFalse(isErrorsEmpty(response));
    }

    @Test
    public void givenIncorrectTelephone3Query_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("4899114-6675");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertFalse(isErrorsEmpty(response));
    }

    @Test
    public void givenIncorrectTelephone4Query_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("(48)991146675");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertFalse(isErrorsEmpty(response));
    }

    @Test
    public void givenIncorrectTelephone5Query_whenHelloWorld_itShouldReturnOkResponse() throws Exception {
        var vars = buildTelephoneVariables("(48)99114.6675");
        GraphQLResponse response = template.postMultipart(telephoneQuery, vars);

        assertThat(response.isOk());
        assertFalse(isErrorsEmpty(response));
    }

    private String buildTelephoneVariables(String telephone) {
        return MessageFormatter.format("{\"phone\": \"{}\" }", telephone).getMessage();
    }

    private boolean isErrorsEmpty(GraphQLResponse response) throws IOException {
        JsonNode jsonData = response.readTree().get("errors");
        return jsonData == null;
    }
}
