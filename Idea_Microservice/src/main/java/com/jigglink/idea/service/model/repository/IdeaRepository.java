package com.jigglink.idea.service.model.repository;

import com.jigglink.idea.service.model.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Integer> {
    boolean existsById(int id);
    List<Idea> findIdeasByConceptId(int conceptId);
    void deleteIdeaByConceptId(int conceptId);
}
