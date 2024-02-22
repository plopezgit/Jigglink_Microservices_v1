package com.jigglink.concept.service.controller;

import com.jigglink.concept.service.model.DTO.ConceptDTO;
import com.jigglink.concept.service.model.service.ConceptService;
import com.jigglink.concept.service.model.service.ItineraryClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concepts")
public class ConceptController {
    @Autowired
    ConceptService conceptService;
    @Autowired
    ItineraryClientService itineraryClientService;

    @Operation(summary = "It creates a concept by a specific itinerary.")
    @PostMapping("/{itineraryId}/itinerary")
    public ResponseEntity<ConceptDTO> createConceptBy(@PathVariable int itineraryId, @RequestBody ConceptDTO newConcept) {
        ConceptDTO conceptDTO = conceptService.createConceptBy(itineraryId, newConcept);
        itineraryClientService.updateItineraryPointsBy(itineraryId, 1);
        return ResponseEntity.ok(conceptDTO);
    }

    @Operation(summary = "It updates an specific concept by a specific itinerary.")
    @PostMapping("/{conceptId}/itinerary/{itineraryId}")
    public ResponseEntity<ConceptDTO> updateConceptBy(@PathVariable int conceptId, @PathVariable int itineraryId, @RequestBody ConceptDTO conceptToUpdate) {
        ConceptDTO conceptDTO = conceptService.updateConceptBy(conceptId, itineraryId, conceptToUpdate);
        itineraryClientService.updateItineraryPointsBy(itineraryId, 4);
        return ResponseEntity.ok(conceptDTO);
    }

    @Operation(summary = "It obtains all the concepts of a specific itinerary.")
    @GetMapping("/itinerary/{itineraryId}")
    public ResponseEntity<List<ConceptDTO>> getConceptBy(@PathVariable int itineraryId) {
        return ResponseEntity.ok(conceptService.getConceptsBy(itineraryId));
    }



}
