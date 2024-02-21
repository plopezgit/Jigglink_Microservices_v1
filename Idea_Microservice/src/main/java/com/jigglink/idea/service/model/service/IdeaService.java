package com.jigglink.idea.service.model.service;

import com.jigglink.idea.service.model.DTO.IdeaDTO;
import com.jigglink.idea.service.model.entity.Idea;
import com.jigglink.idea.service.model.exception.IdeaNotFoundException;
import com.jigglink.idea.service.model.repository.IdeaRepository;
import com.jigglink.idea.service.model.service.interfaces.IdeaServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdeaService implements IdeaServiceInterface {
    @Autowired
    IdeaRepository ideaRepository;
    @Autowired
    private ModelMapper ideaModelMapper;
    @Autowired
    private RestTemplate ideaRestTemplate;

    @Override
    public IdeaDTO createIdeaBy(int conceptId, IdeaDTO newIdea) {
        IdeaDTO ideaToSave = setNewIdeaData(conceptId, newIdea);
        return getIdeaDTOFromEntity(ideaRepository.save(getIdeaEntityFromDTO(ideaToSave)));
    }

    @Override
    public List<IdeaDTO> getIdeasBy(int conceptId) {
        return ideaRepository.findIdeasByConceptId(conceptId).stream().map(this::getIdeaDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<IdeaDTO> getIdeas() {
        return ideaRepository.findAll().stream().map(this::getIdeaDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public IdeaDTO getIdeaBy(int conceptId) {
        return ideaRepository.findById(conceptId).map(this::getIdeaDTOFromEntity)
                .orElseThrow(() -> new IdeaNotFoundException("The idea does not exist"));
    }

    @Override
    public void deleteIdea(int conceptId) {
        ideaRepository.deleteIdeaByConceptId(conceptId);
    }

    private IdeaDTO getIdeaDTOFromEntity(Idea idea) {
        return ideaModelMapper.map(idea, IdeaDTO.class);
    }

    private Idea getIdeaEntityFromDTO(IdeaDTO ideaDTO) {
        return ideaModelMapper.map(ideaDTO, Idea.class);
    }

    private IdeaDTO setNewIdeaData(int conceptId, IdeaDTO ideaToSet){
        return IdeaDTO.builder().conceptId(conceptId).title(ideaToSet.getTitle()).description(ideaToSet.getDescription()).build();
    }
}
