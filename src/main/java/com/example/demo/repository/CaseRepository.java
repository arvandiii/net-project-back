package com.example.demo.repository;

import com.example.demo.entities.CaseEntity;
import com.example.demo.utils.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, Long> {
    @Modifying
    @Query("UPDATE CaseEntity c SET c.status= :status WHERE c.id = :id")
    int updateStatus(@Param("id") long id, @Param("status") CaseStatus status);
}
