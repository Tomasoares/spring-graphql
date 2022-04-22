package com.udemy.springgraphql.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "wad")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String genre;
    private String iwad;
    private LocalDate released;

    @OneToMany(mappedBy = "wad", fetch = FetchType.LAZY)
    private Set<Map> maps;

    @OneToMany(mappedBy = "wad", fetch = FetchType.LAZY)
    private Set<Review> reviews;

}
