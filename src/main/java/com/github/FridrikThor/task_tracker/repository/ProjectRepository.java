package com.github.FridrikThor.task_tracker.repository;

import com.github.FridrikThor.task_tracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.title = ?1")
    Optional<Project> findProjectByTitle(String title);
}
