package com.jigglink.concept.service.model.service.interfaces;

import com.jigglink.concept.service.model.DTO.ConceptDTO;

import java.util.List;

public interface ConceptServiceInterface {
    public ConceptDTO createConceptBy(int itineraryId, ConceptDTO newConcept);
    public List<ConceptDTO> getConceptsBy(int itineraryId);
    public List<ConceptDTO> getConcepts();
    public ConceptDTO getConceptBy(int itineraryId);
    public void deleteConcept(int itineraryId);
}
