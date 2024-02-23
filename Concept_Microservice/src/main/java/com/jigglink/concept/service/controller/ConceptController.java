package com.jigglink.concept.service.controller;

import com.jigglink.concept.service.model.DTO.ConceptDTO;
import com.jigglink.concept.service.model.DTO.IdeaDTO;
import com.jigglink.concept.service.model.service.ConceptService;
import com.jigglink.concept.service.model.service.IdeaClientService;
import com.jigglink.concept.service.model.service.ItineraryClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    IdeaClientService ideaClientService;

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

    @Operation(summary = "It creates an idea by a specific concept.")
    @CircuitBreaker(name="ideaCB", fallbackMethod ="fallbackCreateIdeaBy")
    @PostMapping("/{conceptId}/idea")
    public ResponseEntity<IdeaDTO> createIdeaBy(@PathVariable int conceptId, @RequestBody IdeaDTO newIdea) {
        return ResponseEntity.ok(ideaClientService.createIdeaBy(conceptId, newIdea));
    }

    @Operation(summary = "It updates an idea by a specific concept.")
    @CircuitBreaker(name="ideaCB", fallbackMethod ="fallbackUpdateIdeaBy")
    @PutMapping("/{conceptId}/idea/{ideaId}")
    public ResponseEntity<IdeaDTO> updateIdeaBy(@PathVariable int conceptId, @PathVariable int ideaId, @RequestBody IdeaDTO ideaToUpdate) {
        return ResponseEntity.ok(ideaClientService.updateIdeaBy(conceptId, ideaId, ideaToUpdate));
    }

    @Operation(summary = "It obtains all the ideas of a specific concept.")
    @CircuitBreaker(name="ideaCB", fallbackMethod ="fallbackGetIdeasBy")
    @GetMapping("/{conceptId}/ideas")
    public ResponseEntity<List<IdeaDTO>> getIdeasBy(@PathVariable int conceptId) {
        return ResponseEntity.ok(ideaClientService.getIdeasBy(conceptId));
    }

    @Operation(summary = "It updates the points of a specific itinerary concept.")
    @PostMapping("update/points/concept/{conceptId}")
    public ResponseEntity<ResponseMessage> updateItineraryPointsBy(@PathVariable int conceptId, @RequestBody int point) {
        itineraryClientService.updateItineraryPointsBy(conceptService.getItineraryIdBy(conceptId), point);
        return new ResponseEntity<>(ResponseMessage.builder()
                .responseCode(HttpStatus.ACCEPTED.value())
                .build(), HttpStatus.ACCEPTED);
    }
}
