package com.ntd.arithmeticcalculator.repository;

import com.ntd.arithmeticcalculator.model.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}