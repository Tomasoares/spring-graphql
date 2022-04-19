package com.udemy.springgraphql.service.impl;

import com.udemy.springgraphql.TestApplication;
import com.udemy.springgraphql.graphql.type.Map;
import com.udemy.springgraphql.service.MapService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@ExtendWith(SpringExtension.class)
public class MapServiceImplTest {

    @Autowired
    private MapService service;

    @Test
    public void givenIdWad_whenFindAllMapsByWad_shouldRetrieveList() {
        List<Map> maps = this.service.findAll(UUID.fromString("a15d5780-b37d-11ec-b909-0242ac120002"));

        Assertions.assertFalse(maps.isEmpty());
    }
}
