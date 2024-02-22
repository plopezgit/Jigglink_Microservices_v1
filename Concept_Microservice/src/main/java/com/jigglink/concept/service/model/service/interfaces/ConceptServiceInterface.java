package com.jigglink.concept.service.model.service.interfaces;

import com.jigglink.concept.service.model.DTO.ConceptDTO;

import java.util.List;

public interface ConceptServiceInterface {
    ConceptDTO createConceptBy(int itineraryId, ConceptDTO newConcept);
    ConceptDTO updateConceptBy(int conceptId, int itineraryId, ConceptDTO conceptToUpdate);
    List<ConceptDTO> getConceptsBy(int itineraryId);
    List<ConceptDTO> getConcepts();
    ConceptDTO getConceptBy(int itineraryId);
    void deleteConcept(int itineraryId);
}
