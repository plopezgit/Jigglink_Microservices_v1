package com.jigglink.itinerary.service.model.service.interfaces;

import com.jigglink.itinerary.service.model.DTO.ConceptDTO;

import java.util.List;

public interface ConceptClientServiceInterface {

    ConceptDTO createConceptBy(int itineraryId, ConceptDTO newConcept);
    List<ConceptDTO> getConceptsBy(int itineraryId);
}
