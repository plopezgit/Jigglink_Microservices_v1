package com.jigglink.idea.service.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IdeaDTO {
    private int id;
    private int conceptId;
    private String title;
    private String description;
}
