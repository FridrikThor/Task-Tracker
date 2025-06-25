package com.github.FridrikThor.task_tracker.service;


import com.github.FridrikThor.task_tracker.dto.SignUpDTO;
import com.github.FridrikThor.task_tracker.dto.UserCreateDTO;
import com.github.FridrikThor.task_tracker.dto.UserDTO;
import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.model.User;
import com.github.FridrikThor.task_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(User user) {
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new IllegalStateException("this email is being used");
        }



        /*newUser.setUsername(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setFullName(userDTO.getFullName());
        newUser.setPassword(rawPassword); // In practice, hash this password before saving
        newUser.setActive(true);
        newUser.setCreatedAt(LocalDate.now());*/

        userRepository.save(user);

        //return null;
    }


    /*public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }*/

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("project with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }
}

