package com.example.demo.services;

import com.example.demo.controller.CaseController;
import com.example.demo.controller.UserController;
import com.example.demo.entities.CaseEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.utils.ResponseWithData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/case")
public class CaseService {
    private CaseController caseController;
    private UserController userController;

    @Autowired
    public CaseService(CaseController caseController, UserController userController) {
        this.caseController = caseController;
        this.userController = userController;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/new")
    public Response newCase(@HeaderParam("Authorization") String auth, CaseEntity caseEntity) {
        if (auth == null) {
            return new ResponseWithData<CaseEntity>(false, "invalid token", null).buildResponse();
        }
        // TODO: check authorization and return user
        UserEntity userEntity = null;
        return caseController.newCase(caseEntity, userEntity).buildResponse();
    }
}