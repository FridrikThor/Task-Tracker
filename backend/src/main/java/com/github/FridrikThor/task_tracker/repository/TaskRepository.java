package com.github.FridrikThor.task_tracker.repository;

import com.github.FridrikThor.task_tracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //SELECT * FROM task WHERE task = ?
    //@Query("SELECT t FROM Task t WHERE t.title = ?1")
    Optional<Task> findTaskByTitle(String title);
}
