package com.github.FridrikThor.task_tracker.dto;

public class SignUpDTO {
    private String name;
    private String email;
    private String password;

    public SignUpDTO()
    {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
