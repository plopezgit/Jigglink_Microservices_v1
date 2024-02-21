package com.jigglink.student.service.controller;

import com.jigglink.student.service.model.DTO.StudentDTO;
import com.jigglink.student.service.model.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Operation(summary = "It creates a student.")
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO, WebRequest request) {
        return ResponseEntity.ok(studentService.createStudent(studentDTO));
    }

    @Operation(summary = "It obtains all the students.")
    @GetMapping
    public ResponseEntity<?> getStudents() {
        List<StudentDTO> studentsDTO = studentService.getStudents();
        if (studentsDTO.isEmpty()) {
            return new ResponseEntity<>(ResponseMessage.builder()
                    .responseCode(HttpStatus.NO_CONTENT.value())
                    .message("The students database is empty.").responseTimeStamp(new Date()).build(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
        }
    }

}
