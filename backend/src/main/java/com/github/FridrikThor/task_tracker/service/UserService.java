package com.github.FridrikThor.task_tracker.service;

import com.github.FridrikThor.task_tracker.dto.LoginDTO;
import com.github.FridrikThor.task_tracker.dto.UserDTO;
import com.github.FridrikThor.task_tracker.model.Users;
import com.github.FridrikThor.task_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registerNewUser(Users user) {
        Optional<Users> existingUser = userRepository.findUserByUsername(user.getUsername());

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


    public Users getUserByUsername(String username) {
        /*return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found"));*/

        Optional<Users> existingUser = userRepository.findUserByUsername(username);

        return existingUser.orElseThrow(() -> new IllegalStateException("User not found"));
    }
   /* public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }*/

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("project with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    public String verify(LoginDTO user){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "Failure";
    }
}
