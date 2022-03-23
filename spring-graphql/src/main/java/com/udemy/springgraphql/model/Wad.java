package com.udemy.springgraphql.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Wad {

    private UUID id;
    private String name;

}
