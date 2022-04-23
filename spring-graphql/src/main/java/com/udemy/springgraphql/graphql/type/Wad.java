package com.udemy.springgraphql.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wad {

    private UUID id;
    private String name;
    private String genre;
    private String iwad;
    private LocalDate released;
    private String downloadLink;
}
