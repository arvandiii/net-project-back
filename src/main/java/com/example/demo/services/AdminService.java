package com.example.demo.services;

import com.example.demo.controller.AdminController;
import com.example.demo.controller.UserController;
import com.example.demo.entities.CaseEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.utils.ResponseWithData;
import com.example.demo.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/admin")
public class AdminService {

    private AdminController adminController;
    private UserController userController;

    @Autowired
    public AdminService(AdminController adminController) {
        this.adminController = adminController;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/verifyUser")
    public Response verifyUser(@HeaderParam("Authorization") String token, String username) {
        if (token == null) {
            return new ResponseWithData<CaseEntity>(false, "no token provided", null).buildResponse();
        }
        UserEntity userEntity = userController.getUserByToken(token);
        if (userEntity == null) {
            return new ResponseWithData<CaseEntity>(false, "invalid token", null).buildResponse();
        }
        if (userEntity.getRole() != Role.MANAGER || !userEntity.isVerified()) {
            return new ResponseWithData<CaseEntity>(false, "unauthorized", null).buildResponse();
        }

        return adminController.verifyUser(username).buildResponse();
    }
}
