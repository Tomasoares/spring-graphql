package com.udemy.springgraphql.jpa.repository;

import com.udemy.springgraphql.jpa.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MapRepository extends JpaRepository<Map, UUID> {

    List<Map> findByWadId(final UUID idWad);

    Integer countByWadId(final UUID id);

    @Query("SELECT r.map FROM Review r where r.id = :idReview")
    Optional<Map> findByReviewId(final UUID idReview);

    @Query("SELECT r.map FROM Review r where r.id in (:ids)")
    List<Map> findAllByReviewId(Set<UUID> ids);
}
