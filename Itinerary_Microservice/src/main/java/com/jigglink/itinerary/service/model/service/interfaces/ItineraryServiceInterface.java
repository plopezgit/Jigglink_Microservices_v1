package com.jigglink.itinerary.service.model.service.interfaces;

import com.jigglink.itinerary.service.model.DTO.ItineraryDTO;

import java.util.List;

public interface ItineraryServiceInterface {
    ItineraryDTO createItineraryBy(String studentUsername, ItineraryDTO newItinerary);
    List<ItineraryDTO> getItinerariesBy(String studentUsername);
    long getItineraryRemainingDaysBy(int itineraryId);
    ItineraryDTO updateItineraryPointsBy(int itineraryId, int point);
    List<ItineraryDTO> getItineraries();
    ItineraryDTO getItineraryBy(int itineraryId);
    void deleteItinerary(String studentUsername);
}
