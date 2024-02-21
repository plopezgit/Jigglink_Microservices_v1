package com.jigglink.itinerary.service.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryNotFoundException extends RuntimeException {

    private String message;
}
