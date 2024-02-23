package com.jigglink.idea.service.model.service;

import com.jigglink.idea.service.model.DTO.ConceptDTO;
import com.jigglink.idea.service.model.service.interfaces.ConceptClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConceptClientService implements ConceptClientServiceInterface {
    @Autowired
    private RestTemplate itineraryRestTemplate;

    @Override
    public void updateItineraryPointsBy(int conceptId, int point) {
        itineraryRestTemplate.postForObject("http://concept-service/concepts/update/points/concept/" + conceptId, point, ConceptDTO.class);
    }
}
