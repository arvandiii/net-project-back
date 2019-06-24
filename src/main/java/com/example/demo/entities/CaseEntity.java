package com.example.demo.entities;

import com.example.demo.utils.CaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "_case")
public class CaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private CaseStatus status = CaseStatus.OPEN;
    private String message;

    @ManyToOne
    @JsonIgnore
    private UserEntity userEntity;

    @ManyToOne
    @JsonIgnore
    private UserEntity assignedTo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CaseStatus getStatus() {
        return status;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }

    public UserEntity getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserEntity assignedTo) {
        this.assignedTo = assignedTo;
    }
}
