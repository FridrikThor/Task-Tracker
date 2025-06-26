package com.github.FridrikThor.task_tracker.service;


import com.github.FridrikThor.task_tracker.dto.SignUpDTO;
import com.github.FridrikThor.task_tracker.dto.UserCreateDTO;
import com.github.FridrikThor.task_tracker.dto.UserDTO;
import com.github.FridrikThor.task_tracker.model.Project;
import com.github.FridrikThor.task_tracker.model.Users;
import com.github.FridrikThor.task_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registerNewUser(Users user) {
        Optional<Users> existingUser = userRepository.findUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new IllegalStateException("this email is being used");
        }

        user.setPassword(encoder.encode(user.getPassword()));



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

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("project with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getEmail());
        return "fail";
    }
}

