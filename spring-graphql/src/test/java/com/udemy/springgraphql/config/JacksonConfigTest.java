package com.udemy.springgraphql.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.springgraphql.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class JacksonConfigTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenConfiguredObjectMapper_whenSerializingLocalDateType_shouldConvertSuccessfully() throws JsonProcessingException {
        LocalDate sample = LocalDate.now();
        String result = objectMapper.writeValueAsString(sample);

        assertThat(result, is(notNullValue()));
    }

}
