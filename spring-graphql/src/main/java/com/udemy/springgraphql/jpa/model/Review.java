package com.udemy.springgraphql.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Type(type = "uuid-char")
    private UUID id;

    private String author;
    private String description;
    private int rating;
    private OffsetDateTime published;

    @ManyToOne
    @JoinColumn(name = "wadId")
    private Wad wad;

    @ManyToOne
    @JoinColumn(name = "mapId")
    private Map map;

}
