package com.example.demo.services;

import com.example.demo.controller.UserController;
import com.example.demo.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.entities.CaseEntity;
import com.example.demo.utils.ResponseWithData;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/user")
public class UserService {

    private final UserController userController;

    @Autowired
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

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("Authorization") String token, UserEntity userEntity) {
        if (token == null) {
            return new ResponseWithData<UserEntity>(false, "no token provided", null).buildResponse();
        }
        UserEntity userEntityFromDB = userController.getUserByToken(token);
        if (userEntityFromDB == null) {
            return new ResponseWithData<CaseEntity>(false, "invalid token", null).buildResponse();
        }
        return userController.update(userEntityFromDB, userEntity.getPassword(), userEntity.getEmail()).buildResponse();
    }
}
