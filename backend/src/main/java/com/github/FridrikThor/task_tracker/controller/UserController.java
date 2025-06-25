package com.github.FridrikThor.task_tracker.controller;

import com.github.FridrikThor.task_tracker.dto.ProjectDTO;
import com.github.FridrikThor.task_tracker.dto.SignUpDTO;
import com.github.FridrikThor.task_tracker.dto.UserCreateDTO;
import com.github.FridrikThor.task_tracker.dto.UserDTO;
import com.github.FridrikThor.task_tracker.enums.UserRole;
import com.github.FridrikThor.task_tracker.model.Project;
//import com.github.FridrikThor.task_tracker.model.User;
//import com.github.FridrikThor.task_tracker.service.UserService;
import com.github.FridrikThor.task_tracker.model.Users;
import com.github.FridrikThor.task_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    /*private List<User> users = new ArrayList<>(List.of(
            new User(1L, "Fridrik", "f@gmail.com", "thor", UserRole.ADMIN)
    ));*/

    @GetMapping
    public List<Users> getUsers() {

        return userService.getUsers();
        //return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<Users> registerNewUser(@RequestBody UserCreateDTO userCreateDTO){
        Users newUser = new Users(userCreateDTO);
        userService.registerNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /*private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody SignUpDTO signUpDTO) {
        User newUser = userService.registerNewUser(signUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

@GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UserDTO userDTO) {
        User user = userService.getUserByUsername(username);
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getEmail(), user.getFullName());
        return ResponseEntity.ok(userDTO);
    }

@PutMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUser);
    }*/


}

