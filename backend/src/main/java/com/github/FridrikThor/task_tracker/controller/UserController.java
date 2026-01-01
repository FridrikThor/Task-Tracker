package com.github.FridrikThor.task_tracker.controller;

import com.github.FridrikThor.task_tracker.dto.LoginDTO;
import com.github.FridrikThor.task_tracker.dto.ProjectDTO;
import com.github.FridrikThor.task_tracker.dto.UserCreateDTO;
import com.github.FridrikThor.task_tracker.dto.UserDTO;
import com.github.FridrikThor.task_tracker.enums.UserRole;
import com.github.FridrikThor.task_tracker.model.Project;
//import com.github.FridrikThor.task_tracker.model.User;
//import com.github.FridrikThor.task_tracker.service.UserService;
import com.github.FridrikThor.task_tracker.model.Users;
import com.github.FridrikThor.task_tracker.service.MyUserDetailsService;
import com.github.FridrikThor.task_tracker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /*@GetMapping("/users")
    public List<UserDTO> getUsers(@RequestBody UserDTO user) {

        return userService.getUsers(user);
        //return userService.getUsers();
    }*/
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();

        Users user = userService.getUserByUsername(username);
        System.out.println("þetta eru print lína "+user.getRole());

        // You can return only the safe info
        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity.ok(userDTO);
    }


    /*@GetMapping("/user")
    public List<UserDTO> getUser(@RequestBody UserDTO user) {
        return userService.get
        //return userService.getUsers();
    }*/

    @PostMapping
    public ResponseEntity<Users> registerNewUser(@RequestBody UserCreateDTO userCreateDTO){
        Users newUser = new Users(userCreateDTO);
        userService.registerNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/innskra")
    public Map<String, String> innskra(@RequestBody LoginDTO user){
        return Map.of("token", userService.verify(user));
    }
}

