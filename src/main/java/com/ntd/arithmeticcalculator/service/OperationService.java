package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.OperationEntity;

import java.util.List;
import java.util.Optional;

public interface OperationService {
    OperationEntity saveOperation(OperationEntity operationEntity);
    Optional<OperationEntity> findById(Long id);
    List<OperationEntity> findAll();
    Optional<OperationEntity> update(Long id, OperationEntity operationEntity);
    void deleteOperation(Long id);
}