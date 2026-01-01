package com.github.FridrikThor.task_tracker.dto;

import com.github.FridrikThor.task_tracker.enums.UserRole;
import com.github.FridrikThor.task_tracker.model.Users;

import java.util.List;

public class UserDTO {
    private Long id;

    private String name;
    private String username;
    private UserRole role;

    public UserDTO(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.role = user.getRole();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
