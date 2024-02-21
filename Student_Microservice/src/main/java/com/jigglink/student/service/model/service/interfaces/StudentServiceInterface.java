package com.jigglink.student.service.model.service.interfaces;

import com.jigglink.student.service.model.DTO.ItineraryDTO;
import com.jigglink.student.service.model.DTO.StudentDTO;

import java.util.List;

public interface StudentServiceInterface {
    public List<StudentDTO> getStudents ();
    public StudentDTO createStudent (StudentDTO user);
    public StudentDTO getStudent (String username);
    public List<ItineraryDTO> getItinerariesBy(String username);
    public ItineraryDTO createItineraryBy(String username, ItineraryDTO newItinerary);
}
