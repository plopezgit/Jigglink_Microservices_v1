package com.jigglink.concept.service.model.repository;

import com.jigglink.concept.service.model.entity.Concept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Integer> {
    boolean existsById(int id);
    List<Concept> findConceptsByItineraryId(int itineraryId);
    void deleteConceptByItineraryId(int itineraryId);
}
