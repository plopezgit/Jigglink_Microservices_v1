package com.jigglink.itinerary.service.controller;

import com.jigglink.itinerary.service.model.DTO.ItineraryDTO;
import com.jigglink.itinerary.service.model.service.ItineraryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itineraries")
public class ItineraryController {
    @Autowired
    ItineraryService itineraryService;

    @Operation(summary = "It creates an itinerary by a specific student.")
    @PostMapping("/{studentUsername}/student")
    public ResponseEntity<ItineraryDTO> createItineraryBy(@PathVariable String studentUsername, @RequestBody ItineraryDTO newItinerary) {
        ItineraryDTO itineraryDTO = itineraryService.createItineraryBy(studentUsername, newItinerary);
        return ResponseEntity.ok(itineraryDTO);
    }

    @Operation(summary = "It obtains all the itineraries of a specific student.")
    @GetMapping("/student/{studentUsername}")
    public ResponseEntity<?> getItinerariesBy(@PathVariable String studentUsername) {
        return ResponseEntity.ok(itineraryService.getItinerariesBy(studentUsername));
    }
}
