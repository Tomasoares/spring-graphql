package com.udemy.springgraphql.jpa.repository;

import com.udemy.springgraphql.jpa.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findAllByWadId(final UUID wadId, final Pageable page);

    List<Review> findAllByMapId(final UUID mapId, final Pageable page);

    @Query("SELECT r FROM Review r where r.id in (:ids)")
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "map")
    List<Review> findAllByIdPlusMaps(Set<UUID> ids);

    @Query("SELECT r FROM Review r where r.id in (:ids)")
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "wad")
    List<Review> findAllByIdPlusWads(Set<UUID> ids);
}
