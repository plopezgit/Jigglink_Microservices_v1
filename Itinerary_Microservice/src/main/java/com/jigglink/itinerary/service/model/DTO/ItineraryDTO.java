package com.jigglink.itinerary.service.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItineraryDTO {
    private int id;
    private String studentUsername;
    private String title;
    private LocalDate targetDate;
    private int points;
}
