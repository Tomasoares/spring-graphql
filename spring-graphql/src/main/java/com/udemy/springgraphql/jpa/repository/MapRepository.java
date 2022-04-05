package com.udemy.springgraphql.jpa.repository;

import com.udemy.springgraphql.jpa.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Repository
public interface MapRepository extends JpaRepository<Map, UUID> {

    List<Map> findByWadId(UUID idWad);

}
