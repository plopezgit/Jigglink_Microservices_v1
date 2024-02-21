package com.jigglink.badge.service.model.repository;

import com.jigglink.badge.service.model.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, String> {
    boolean existsByName(String name);
    List<Badge> findBadgesByStudentUsername(String studentUsername);
    void deleteBadgesByStudentUsername(String studentUsername);
}
