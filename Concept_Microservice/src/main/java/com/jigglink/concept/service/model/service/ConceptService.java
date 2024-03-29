package com.jigglink.concept.service.model.service;

import com.jigglink.concept.service.model.entity.Concept;
import com.jigglink.concept.service.model.DTO.ConceptDTO;
import com.jigglink.concept.service.model.exception.ConceptNotFoundException;
import com.jigglink.concept.service.model.repository.ConceptRepository;
import com.jigglink.concept.service.model.service.interfaces.ConceptServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConceptService implements ConceptServiceInterface {
    @Autowired
    ConceptRepository conceptRepository;
    @Autowired
    private ModelMapper conceptModelMapper;
    @Autowired
    private RestTemplate conceptRestTemplate;

    @Override
    public ConceptDTO createConceptBy(int itineraryId, ConceptDTO newConcept) {
        ConceptDTO conceptToSave = setNewConceptDataBy(itineraryId, newConcept);
        return getConceptDTOFromEntity(conceptRepository.save(getConceptEntityFromDTO(conceptToSave)));
    }

    @Override
    public ConceptDTO updateConceptBy(int conceptId, int itineraryId, ConceptDTO conceptToUpdate) {
        ConceptDTO concept = getConceptBy(conceptId);
        ConceptDTO updatedConcept = getConceptDTOFromEntity(conceptRepository.save(getConceptEntityFromDTO(updateConceptDataBy(concept, conceptToUpdate))));
        return updatedConcept;
    }

    @Override
    public List<ConceptDTO> getConceptsBy(int itineraryId) {
        return conceptRepository.findConceptsByItineraryId(itineraryId).stream().map(this::getConceptDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ConceptDTO> getConcepts() {
        return conceptRepository.findAll().stream().map(this::getConceptDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public ConceptDTO getConceptBy(int itineraryId) {
        return conceptRepository.findById(itineraryId).map(this::getConceptDTOFromEntity)
                .orElseThrow(() -> new ConceptNotFoundException("The concept does not exist"));
    }

    @Override
    public void deleteConcept(int itineraryId) {
        conceptRepository.deleteConceptByItineraryId(itineraryId);
    }

    @Override
    public int getItineraryIdBy(int conceptId) {
        return conceptRepository.findById(conceptId).get().getItineraryId();
    }

    private ConceptDTO getConceptDTOFromEntity(Concept concept) {
        return conceptModelMapper.map(concept, ConceptDTO.class);
    }

    private Concept getConceptEntityFromDTO(ConceptDTO conceptDTO) {
        return conceptModelMapper.map(conceptDTO, Concept.class);
    }

    private ConceptDTO setNewConceptDataBy(int itineraryId, ConceptDTO conceptToSet){
        return ConceptDTO.builder().itineraryId(itineraryId).title(conceptToSet.getTitle()).effort(conceptToSet.getEffort()).notes(conceptToSet.getNotes()).build();
    }
    private ConceptDTO updateConceptDataBy(ConceptDTO concept, ConceptDTO conceptToUpdate){
        return ConceptDTO.builder()
                .id(concept.getId())
                .itineraryId(concept.getItineraryId())
                .title(concept.getTitle())
                .effort(concept.getEffort())
                .notes(concept.getNotes() + conceptToUpdate.getNotes() + " | ")
                .whatIs(concept.getWhatIs() + conceptToUpdate.getWhatIs() + " | ")
                .howIs(concept.getHowIs() + conceptToUpdate.getHowIs() + " | ")
                .whyIs(concept.getWhyIs() + conceptToUpdate.getWhyIs() + " | ")
                .updateCounter(concept.getUpdateCounter() + 1)
                .build();
    }
}
