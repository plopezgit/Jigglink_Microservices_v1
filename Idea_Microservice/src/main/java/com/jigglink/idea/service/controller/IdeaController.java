package com.jigglink.idea.service.controller;

import com.jigglink.idea.service.model.DTO.IdeaDTO;
import com.jigglink.idea.service.model.service.ConceptClientService;
import com.jigglink.idea.service.model.service.IdeaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ideas")
public class IdeaController {

    @Autowired
    IdeaService ideaService;
    @Autowired
    ConceptClientService conceptClientService;

    @Operation(summary = "It creates an idea by a specific concept.")
    @PostMapping("/{conceptId}/concept")
    public ResponseEntity<IdeaDTO> createIdeaBy(@PathVariable int conceptId, @RequestBody IdeaDTO newIdea) {
        IdeaDTO ideaDTO = ideaService.createIdeaBy(conceptId, newIdea);
        conceptClientService.updateItineraryPointsBy(conceptId, 4);
        return ResponseEntity.ok(ideaDTO);
    }

    @Operation(summary = "It updates an specific idea by a specific concept.")
    @PutMapping("/{ideaId}/concept/{conceptId}")
    public ResponseEntity<IdeaDTO> updateIdeaBy(@PathVariable int ideaId, @PathVariable int conceptId, @RequestBody IdeaDTO ideaToUpdate) {
        IdeaDTO ideaDTO = ideaService.updateIdeaBy(ideaId, conceptId, ideaToUpdate);
        conceptClientService.updateItineraryPointsBy(conceptId, 6);
        return ResponseEntity.ok(ideaDTO);
    }

    @Operation(summary = "It obtains all the ideas of a specific concept.")
    @GetMapping("/concept/{conceptId}")
    public ResponseEntity<List<IdeaDTO>> getIdeasBy(@PathVariable int conceptId) {
        return ResponseEntity.ok(ideaService.getIdeasBy(conceptId));
    }

}
