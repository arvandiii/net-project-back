package com.example.demo.controller;

import com.example.demo.entities.TokenEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResponseWithData;
import com.example.demo.utils.Role;
import com.example.demo.utils.Utils;
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

        if (userEntity.getRole() == Role.STUDENT) {
            userEntity.setVerified(true);
        }
        userEntity.setPassword(Utils.hash(userEntity.getPassword()));
        userRepository.save(userEntity);

        return new ResponseWithData<>(true, "user registered", userEntity);
    }

    public ResponseWithData<TokenEntity> login(UserEntity userEntity) {
        UserEntity userFromDB = userRepository.findByUsername(userEntity.getUsername());

        if (userFromDB == null) {
            return new ResponseWithData<>(false, "username doesnt exists", null);
        }

        if (!userFromDB.getPassword().equals(Utils.hash(userEntity.getPassword()))) {
            return new ResponseWithData<>(false, "password incorrect", null);
        }

        TokenEntity tokenEntity = new TokenEntity(userFromDB);
        tokenRepository.save(tokenEntity);

        return new ResponseWithData<>(true, "login", tokenEntity);
    }

    public ResponseWithData<UserEntity> update(UserEntity userEntity, String newPassword, String newEmail) {

        if (newPassword == null && newEmail == null)
            return new ResponseWithData<>(false, "username or email should be provided", null);

        if (newPassword != null)
            userEntity.setPassword(Utils.hash(newPassword));
        if (newEmail != null)
            userEntity.setEmail(newEmail);
        userRepository.save(userEntity);

        return new ResponseWithData<>(true, "user updated", userEntity);
    }

    public UserEntity getUserByToken(String token) {
        TokenEntity tokenEntity = tokenRepository.findByToken(token);
        return tokenEntity.getUserEntity();
    }
}
