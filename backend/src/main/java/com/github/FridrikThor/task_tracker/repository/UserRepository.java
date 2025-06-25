package com.github.FridrikThor.task_tracker.repository;

import com.github.FridrikThor.task_tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    //Optional<User> findByUsername(String username);
    Optional<Users> findUserByEmail(String email);
    Users findByEmail(String email);
}
