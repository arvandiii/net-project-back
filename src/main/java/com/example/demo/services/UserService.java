package com.example.demo.services;

import com.example.demo.controller.UserController;
import com.example.demo.entities.UserEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserService {

    private final UserController userController;

    public UserService(UserController userController){
        this.userController = userController;
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public String register(UserEntity user){
        return user.getName();
    }

}
