package com.github.FridrikThor.task_tracker.dto;

import com.github.FridrikThor.task_tracker.enums.UserRole;
import com.github.FridrikThor.task_tracker.model.Users;

import java.util.List;

public class UserDTO {
    private Long id;

    private String name;
    private String email;
    private UserRole role;

    public UserDTO(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
