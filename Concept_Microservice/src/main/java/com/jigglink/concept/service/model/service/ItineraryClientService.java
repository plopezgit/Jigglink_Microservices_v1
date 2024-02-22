package com.jigglink.concept.service.model.service;

import com.jigglink.concept.service.model.DTO.ItineraryDTO;
import com.jigglink.concept.service.model.service.interfaces.ItineraryClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItineraryClientService implements ItineraryClientServiceInterface {
    @Autowired
    private RestTemplate itineraryRestTemplate;

    @Override
    public void updateItineraryPointsBy(int itineraryId, int point) {
        itineraryRestTemplate.postForObject("http://itinerary-service/itineraries/update/points/" + itineraryId, point, ItineraryDTO.class);
    }
}
