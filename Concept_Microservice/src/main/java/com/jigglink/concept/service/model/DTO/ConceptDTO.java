package com.jigglink.concept.service.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConceptDTO {

    private int id;
    private int itineraryId;
    private String title;
    private String effort;
    private String notes;
    private String whatIs;
    private String howIs;
    private String whyIs;
    private int updateCounter;
}
