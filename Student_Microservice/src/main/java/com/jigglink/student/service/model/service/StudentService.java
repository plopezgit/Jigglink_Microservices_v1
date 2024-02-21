package com.jigglink.student.service.model.service;

import com.jigglink.student.service.model.entity.Student;
import com.jigglink.student.service.model.DTO.StudentDTO;
import com.jigglink.student.service.model.exception.*;
import com.jigglink.student.service.model.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements com.jigglink.student.service.model.service.interfaces.StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper studentModelMapper;
    @Autowired
    private RestTemplate studentRestTemplate;

    @Override
    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::getStudentDTOFromEntity).collect(Collectors.toList());
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        StudentDTO newStudent = StudentDTO.builder().username(setUsernameTo(studentDTO).getUsername()).build();
        if (existUsername(newStudent) && !isAnonymous(newStudent))
            throw new UsernameAlreadyExistException("The username already exist, please try another one.");
        studentRepository.save(getStudentEntityFromDTO(newStudent));
        return newStudent;
    }

    @Override
    public StudentDTO getStudent(String username) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteStudent(String username) {
        return null;
    }

    private StudentDTO getStudentDTOFromEntity(Student student) {
        return studentModelMapper.map(student, StudentDTO.class);
    }

    private Student getStudentEntityFromDTO(StudentDTO studentDTO) {
        return studentModelMapper.map(studentDTO, Student.class);
    }

    private boolean isUsernameEmptyOf(StudentDTO studentToCheck) {
        return studentToCheck.getUsername().isEmpty() || studentToCheck.getUsername().isBlank();
    }

    private boolean existUsername(StudentDTO studentToCheck) {
        return studentRepository.existsByUsername(studentToCheck.getUsername());
    }

    private boolean isAnonymous(StudentDTO studentToCheck) {
        return studentToCheck.getUsername().equalsIgnoreCase("Anonymous");
    }

    private StudentDTO setUsernameTo (StudentDTO studentToSet) {
        return isUsernameEmptyOf(studentToSet) ?
                StudentDTO.builder().username("Anonymous").build() :
                StudentDTO.builder().username(studentToSet.getUsername()).build();
    }
}
