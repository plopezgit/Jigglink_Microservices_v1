package com.jigglink.idea.service.model.service.interfaces;

import com.jigglink.idea.service.model.DTO.IdeaDTO;

import java.util.List;

public interface IdeaServiceInterface {
    public IdeaDTO createIdeaBy(int conceptId, IdeaDTO newIdea);
    IdeaDTO updateIdeaBy(int conceptId, int ideaId, IdeaDTO ideaToUpdate);
    public List<IdeaDTO> getIdeasBy(int conceptId);
    public List<IdeaDTO> getIdeas();
    public IdeaDTO getIdeaBy(int conceptId);
    public void deleteIdea(int conceptId);
}
