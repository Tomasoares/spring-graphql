package com.udemy.springgraphql.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "map")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String author;
    private Integer enemies;
    private OffsetTime partime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wad_id")
    private Wad wad;

    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY)
    private Set<Review> reviews;

}
