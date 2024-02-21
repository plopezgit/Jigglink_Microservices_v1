package com.jigglink.itinerary.service.model.service;

import com.jigglink.itinerary.service.model.entity.Itinerary;
import com.jigglink.itinerary.service.model.DTO.ItineraryDTO;
import com.jigglink.itinerary.service.model.exception.ItineraryNotFoundException;
import com.jigglink.itinerary.service.model.repository.ItineraryRepository;
import com.jigglink.itinerary.service.model.service.interfaces.ItineraryServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItineraryService implements ItineraryServiceInterface {

    @Autowired
    ItineraryRepository itineraryRepository;
    @Autowired
    private ModelMapper itineraryModelMapper;
    @Autowired
    private RestTemplate itineraryRestTemplate;

    @Override
    public ItineraryDTO createItineraryBy(String studentUsername, ItineraryDTO newItinerary) {
        ItineraryDTO itineraryToSave = setNewItineraryData(studentUsername, newItinerary);
        return getItineraryDTOFromEntity(itineraryRepository.save(getItineraryEntityFromDTO(itineraryToSave)));
    }

    @Override
    public List<ItineraryDTO> getItinerariesBy(String studentUsername) {
        return itineraryRepository.findItinerariesByStudentUsername(studentUsername).stream().map(this::getItineraryDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ItineraryDTO> getItineraries() {
        return itineraryRepository.findAll().stream().map(this::getItineraryDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public ItineraryDTO getItineraryBy(int itineraryId) {
        return itineraryRepository.findById(itineraryId).map(this::getItineraryDTOFromEntity)
                .orElseThrow(() -> new ItineraryNotFoundException("The itinerary does not exist"));
    }

    public long getRemainingDaysBy(int itineraryId) {
        return calculateRemainingDaysOf(itineraryRepository.findById(itineraryId).map(this::getItineraryDTOFromEntity)
                .orElseThrow(() -> new ItineraryNotFoundException("The itinerary does not exist")));
    }

    @Override
    public void deleteItinerary(String studentUsername) {
        itineraryRepository.deleteItineraryByStudentUsername(studentUsername);
    }

    private ItineraryDTO getItineraryDTOFromEntity(Itinerary itinerary) {
        return itineraryModelMapper.map(itinerary, ItineraryDTO.class);
    }

    private Itinerary getItineraryEntityFromDTO(ItineraryDTO itineraryDTO) {
        return itineraryModelMapper.map(itineraryDTO, Itinerary.class);
    }

    private ItineraryDTO setNewItineraryData(String username, ItineraryDTO itineraryToSet){
        return ItineraryDTO.builder().studentUsername(username).title(itineraryToSet.getTitle()).targetDate(itineraryToSet.getTargetDate()).points(0).build();
    }

    private long calculateRemainingDaysOf (ItineraryDTO itineraryToCalculate) {
        return LocalDate.now().until(itineraryToCalculate.getTargetDate(), ChronoUnit.DAYS);
    }
}
