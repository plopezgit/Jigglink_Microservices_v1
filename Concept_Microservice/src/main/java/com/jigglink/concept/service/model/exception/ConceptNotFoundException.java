package com.jigglink.concept.service.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConceptNotFoundException extends RuntimeException {

    private String message;
}
