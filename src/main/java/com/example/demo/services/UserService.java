package com.example.demo.services;

import com.example.demo.controller.UserController;
import com.example.demo.entities.UserEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/user")
public class UserService {

    private final UserController userController;

    public UserService(UserController userController) {
        this.userController = userController;
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserEntity userEntity) {
        System.out.println("injaaaaaaa");
        return userController.register(userEntity).buildResponse();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserEntity userEntity) {
        return userController.login(userEntity).buildResponse();
    }
}
