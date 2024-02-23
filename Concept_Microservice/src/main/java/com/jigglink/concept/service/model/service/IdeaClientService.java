package com.jigglink.concept.service.model.service;

import com.jigglink.concept.service.model.DTO.IdeaDTO;
import com.jigglink.concept.service.model.exception.ConceptNotFoundException;
import com.jigglink.concept.service.model.repository.ConceptRepository;
import com.jigglink.concept.service.model.service.interfaces.IdeaClientServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IdeaClientService implements IdeaClientServiceInterface {
    @Autowired
    private ConceptRepository conceptRepository;
    @Autowired
    private ModelMapper conceptModelMapper;
    @Autowired
    private RestTemplate ideaRestTemplate;

    @Override
    public IdeaDTO createIdeaBy(int conceptId, IdeaDTO newIdea) {
        if (!conceptRepository.existsById(conceptId)) {
            throw new ConceptNotFoundException("The concept does not exists.");
        } else {
            return ideaRestTemplate.postForObject("http://idea-service/ideas/"+ conceptId +"/concept", newIdea, IdeaDTO.class);
        }
    }

    @Override
    public List<IdeaDTO> getIdeasBy(int conceptId) {
        return ideaRestTemplate.getForObject("http://idea-service/ideas/concept/" + conceptId, List.class);
    }

    @Override
    public IdeaDTO updateIdeaBy(int ideaId, int conceptId, IdeaDTO ideaToUpdate) {
        if (!conceptRepository.existsById(conceptId)) {
            throw new ConceptNotFoundException("The itinerary does not exists.");
        } else {
            return ideaRestTemplate.postForObject("http://idea-service/ideas/"+ ideaId +"/concept/"+ conceptId, ideaToUpdate, IdeaDTO.class);
        }
    }
}
