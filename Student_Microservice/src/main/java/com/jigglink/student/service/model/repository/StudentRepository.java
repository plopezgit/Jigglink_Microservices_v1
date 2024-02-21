package com.jigglink.student.service.model.repository;

import com.jigglink.student.service.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository <Student, String> {
    boolean existsByUsername(String username);
}
