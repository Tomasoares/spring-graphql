package com.udemy.springgraphql.graphql.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WadInput {

    private String name;
    private String iwad;
    private String genre;
    private LocalDate released;
    private String downloadLink;

}
