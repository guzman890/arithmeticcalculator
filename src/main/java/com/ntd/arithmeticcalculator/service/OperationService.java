package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.Operation;
import java.util.List;
import java.util.Optional;

public interface OperationService {
    Operation saveOperation(Operation operation);
    Optional<Operation> findById(Long id);
    List<Operation> findAll();
    Optional<Operation> update(Long id, Operation operation);
    void deleteOperation(Long id);
}