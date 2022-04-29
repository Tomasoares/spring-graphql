package com.udemy.springgraphql.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    private UUID id;
    private String author;
    private String description;
    private int rating;
    private OffsetDateTime published;
    private UUID idMap;

}
