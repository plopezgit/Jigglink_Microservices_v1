package com.jigglink.itinerary.service.model.service;

import com.jigglink.itinerary.service.model.DTO.ConceptDTO;
import com.jigglink.itinerary.service.model.exception.ItineraryNotFoundException;
import com.jigglink.itinerary.service.model.repository.ItineraryRepository;
import com.jigglink.itinerary.service.model.service.interfaces.ConceptClientServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConceptClientService implements ConceptClientServiceInterface {
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private ModelMapper itineraryModelMapper;
    @Autowired
    private RestTemplate itineraryRestTemplate;
    @Override
    public ConceptDTO createConceptBy(int itineraryId, ConceptDTO newConcept) {
        if (!itineraryRepository.existsById(itineraryId)) {
            throw new ItineraryNotFoundException("The itinerary does not exists.");
        } else {
            return itineraryRestTemplate.postForObject("http://concept-service/concepts/"+ itineraryId +"/itinerary", newConcept, ConceptDTO.class);
        }
    }

    @Override
    public List<ConceptDTO> getConceptsBy(int itineraryId) {
        return itineraryRestTemplate.getForObject("http://concept-service/concepts/itinerary/" + itineraryId, List.class);
    }

    @Override
    public ConceptDTO updateConceptBy(int conceptId, int itineraryId, ConceptDTO conceptToUpdate) {
        if (!itineraryRepository.existsById(itineraryId)) {
            throw new ItineraryNotFoundException("The itinerary does not exists.");
        } else {
            return itineraryRestTemplate.postForObject("http://concept-service/concepts/"+ conceptId +"/itinerary/"+ itineraryId, conceptToUpdate, ConceptDTO.class);
        }
    }
}
