package com.example.demo.controller;


import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity register(String name) {
        return repository.save(new UserEntity(name));
    }


}
