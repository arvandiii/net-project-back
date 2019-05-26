package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "token")
public class TokenEntity {
    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @ManyToOne
    @JsonIgnore
    private UserEntity userEntity;

    public TokenEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.token = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
