package com.udemy.springgraphql.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    private UUID id;
    private String name;
    private int enemies;
    private String author;
    private OffsetTime partime;

}
