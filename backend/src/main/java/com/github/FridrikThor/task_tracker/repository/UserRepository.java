package com.github.FridrikThor.task_tracker.repository;

import com.github.FridrikThor.task_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);
    User findByEmail(String email);
}
