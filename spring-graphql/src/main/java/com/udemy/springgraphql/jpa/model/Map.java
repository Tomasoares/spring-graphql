package com.udemy.springgraphql.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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
    private int enemies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wad_id")
    private Wad wad;

}
