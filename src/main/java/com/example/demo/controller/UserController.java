package com.example.demo.controller;


import com.example.demo.entities.TokenEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResponseWithData;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    private boolean isUserDataValid(UserEntity userEntity) {
        if (userEntity.getUsername() == null) {
            return false;
        }
        if (userEntity.getEmail() == null) {
            return false;
        }
        if (userEntity.getPassword() == null) {
            return false;
        }
        return true;
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public ResponseWithData<UserEntity> register(UserEntity userEntity) {
        if (!isUserDataValid(userEntity)) {
            return new ResponseWithData<>(false, "invalid user data", null);
        }

        if (usernameExists(userEntity.getUsername())) {
            return new ResponseWithData<>(false, "username exists", null);
        }

        if (emailExists(userEntity.getEmail())) {
            return new ResponseWithData<>(false, "email exists", null);
        }

        userRepository.save(userEntity);

        return new ResponseWithData<>(true, "user registered", userEntity);
    }

    public ResponseWithData<TokenEntity> login(UserEntity userEntity) {
        UserEntity userFromDB = userRepository.findByUsername(userEntity.getUsername());

        if (userFromDB == null) {
            return new ResponseWithData<>(false, "username doesnt exists", null);
        }

        if (userFromDB.getPassword() != userEntity.getPassword()) {
            return new ResponseWithData<>(false, "password incorrect", null);
        }

        TokenEntity tokenEntity = new TokenEntity(userEntity);
        tokenRepository.save(tokenEntity);

        return new ResponseWithData<>(true, "login", tokenEntity);
    }
}
