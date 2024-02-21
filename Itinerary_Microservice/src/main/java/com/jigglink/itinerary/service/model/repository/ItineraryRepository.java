package com.jigglink.itinerary.service.model.repository;

import com.jigglink.itinerary.service.model.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository <Itinerary, Integer> {
    boolean existsById(int id);
    List<Itinerary> findItinerariesByStudentUsername(String studentUsername);
    void deleteItineraryByStudentUsername(String studentUsername);
}
