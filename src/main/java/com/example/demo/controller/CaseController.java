package com.example.demo.controller;

import com.example.demo.entities.CaseEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.CaseRepository;
import com.example.demo.utils.CaseStatus;
import com.example.demo.utils.ResponseWithData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class CaseController {
    private CaseRepository caseRepository;

    @Autowired
    public CaseController(CaseRepository caseRepository) { this.caseRepository= caseRepository; }

    public ResponseWithData<CaseEntity> newCase(CaseEntity caseEntity, UserEntity userEntity) {
        caseEntity.setUserEntity(userEntity);
        return new ResponseWithData<>(true, "case created", caseRepository.save(caseEntity));
    }

    @Transactional
    public ResponseWithData<Integer> updateCase(long id, CaseStatus status){
        return new ResponseWithData<>(true, "case status changed",caseRepository.updateStatus(id, status) );
    }
}
