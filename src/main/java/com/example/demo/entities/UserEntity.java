package com.example.demo.entities;


import com.example.demo.utils.Utils;
import lombok.Data;

import javax.persistence.*;

enum Role {
    STUDENT,
    PROFESSOR,
    STAFF,
    MANAGER
}

@Data
@Entity
@Table(name = "user")
public class UserEntity {

    private @Id
    @GeneratedValue
    Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

    private void setPassword(String password) {
        this.password = Utils.hash(password);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}