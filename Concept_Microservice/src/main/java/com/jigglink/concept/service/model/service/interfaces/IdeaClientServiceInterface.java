package com.jigglink.concept.service.model.service.interfaces;

import com.jigglink.concept.service.model.DTO.IdeaDTO;

import java.util.List;

public interface IdeaClientServiceInterface {
    IdeaDTO createIdeaBy(int conceptId, IdeaDTO newIdea);
    List<IdeaDTO> getIdeasBy(int conceptId);
    IdeaDTO updateIdeaBy(int ideaId, int conceptId, IdeaDTO ideaToUpdate);
}
