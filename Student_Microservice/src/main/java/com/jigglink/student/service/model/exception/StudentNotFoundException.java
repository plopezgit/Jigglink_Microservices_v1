package com.jigglink.student.service.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentNotFoundException extends RuntimeException {

    private String message;
}
