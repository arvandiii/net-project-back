package com.example.demo.controller;

import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResponseWithData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    private UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseWithData<UserEntity> verifyUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setVerified(true);
        return new ResponseWithData<>(true, "user verified", userRepository.save(userEntity));
    }
}
