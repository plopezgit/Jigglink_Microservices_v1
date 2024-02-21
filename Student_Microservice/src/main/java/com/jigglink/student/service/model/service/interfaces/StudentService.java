package com.jigglink.student.service.model.service.interfaces;

import com.jigglink.student.service.model.DTO.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    public List<StudentDTO> getStudents ();
    public StudentDTO createStudent (StudentDTO user);
    public StudentDTO getStudent (String username);
    public ResponseEntity<Void> deleteStudent (String username);
}
