package com.jigglink.concept.service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "concept")
public class Concept {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
