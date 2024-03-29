package com.example.demo;


import com.example.demo.services.AdminService;
import com.example.demo.services.CaseService;
import com.example.demo.services.UserService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(UserService.class);
        register(CaseService.class);
        register(AdminService.class);
    }
}

