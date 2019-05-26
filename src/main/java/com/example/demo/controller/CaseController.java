package com.example.demo.controller;

import com.example.demo.entities.CaseEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.CaseRepository;
import com.example.demo.utils.ResponseWithData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CaseController {
    private CaseRepository caseRepository;

    @Autowired
    public CaseController(CaseRepository caseRepository) { this.caseRepository= caseRepository; }

    public ResponseWithData<CaseEntity> newCase(CaseEntity caseEntity, UserEntity userEntity) {
        caseEntity.setUserEntity(userEntity);
        CaseEntity savedEntry = caseRepository.save(caseEntity);
        return new ResponseWithData<>(true, "case created", savedEntry);
    }
}
