package com.jigglink.itinerary.service.controller;

import com.jigglink.itinerary.service.model.DTO.ConceptDTO;
import com.jigglink.itinerary.service.model.DTO.ItineraryDTO;
import com.jigglink.itinerary.service.model.service.ConceptClientService;
import com.jigglink.itinerary.service.model.service.ItineraryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    ItineraryService itineraryService;
    @Autowired
    ConceptClientService conceptClientService;

    @Operation(summary = "It creates an itinerary by a specific student.")
    @PostMapping("/{studentUsername}/student")
    public ResponseEntity<ItineraryDTO> createItineraryBy(@PathVariable String studentUsername, @RequestBody ItineraryDTO newItinerary) {
        ItineraryDTO itineraryDTO = itineraryService.createItineraryBy(studentUsername, newItinerary);
        return ResponseEntity.ok(itineraryDTO);
    }

    @Operation(summary = "It obtains all the itineraries of a specific student.")
    @GetMapping("/student/{studentUsername}")
    public ResponseEntity<List<ItineraryDTO>> getItinerariesBy(@PathVariable String studentUsername) {
        return ResponseEntity.ok(itineraryService.getItinerariesBy(studentUsername));
    }

    @Operation(summary = "It obtains the itinerary information by an specific itinerary identification.")
    @GetMapping("/{itineraryId}")
    public ResponseEntity<ItineraryDTO> getItineraryBy(@PathVariable int itineraryId) {
        return ResponseEntity.ok(itineraryService.getItineraryBy(itineraryId));
    }

    @Operation(summary = "It obtains total remaining days by an specific itinerary.")
    @GetMapping("/{itineraryId}/days")
    public ResponseEntity<Long> getRemainingDaysBy(@PathVariable int itineraryId) {
        return ResponseEntity.ok(itineraryService.getItineraryRemainingDaysBy(itineraryId));
    }

    @Operation(summary = "It creates a concept by a specific itinerary.")
    @CircuitBreaker(name="conceptCB", fallbackMethod ="fallbackCreateConceptBy")
    @PostMapping("/{itineraryId}/concept")
    public ResponseEntity<ConceptDTO> createConceptBy(@PathVariable int itineraryId, @RequestBody ConceptDTO newConcept) {
        return ResponseEntity.ok(conceptClientService.createConceptBy(itineraryId, newConcept));
    }

    @Operation(summary = "It obtains all the concepts of a specific itinerary.")
    @CircuitBreaker(name="conceptCB", fallbackMethod ="fallbackGetConceptsBy")
    @GetMapping("/{itineraryId}/concepts")
    public ResponseEntity<List<ConceptDTO>> getConceptBy(@PathVariable int itineraryId) {
        return ResponseEntity.ok(conceptClientService.getConceptsBy(itineraryId));
    }

}
