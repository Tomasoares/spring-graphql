package com.udemy.springgraphql.jpa.repository;

import com.udemy.springgraphql.jpa.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findAllByWadId(final UUID wadId, final Pageable page);

    List<Review> findAllByMapId(final UUID mapId, final Pageable page);

}
