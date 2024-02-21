package com.jigglink.itinerary.service.model.service.interfaces;

import com.jigglink.itinerary.service.model.DTO.ItineraryDTO;

import java.util.List;

public interface ItineraryServiceInterface {
    public ItineraryDTO createItineraryBy(String studentUsername, ItineraryDTO newItinerary);
    public List<ItineraryDTO> getItinerariesBy(String studentUsername);
    public long getRemainingDaysBy(int id);
    public List<ItineraryDTO> getItineraries();
    public ItineraryDTO getItineraryBy(int itineraryId);
    public void deleteItinerary(String studentUsername);
}
