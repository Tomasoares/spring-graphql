package com.udemy.springgraphql.jpa.repository;

import com.udemy.springgraphql.jpa.model.Wad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WadRepository extends JpaRepository<Wad, UUID> {

    @Query("SELECT m.wad FROM Map m WHERE m.id = :idMap")
    Optional<Wad> findByMapId(final UUID idMap);

    @Query("SELECT r.wad FROM Review r WHERE r.id = :idReview")
    Optional<Wad> findByReviewId(final UUID idReview);
}
