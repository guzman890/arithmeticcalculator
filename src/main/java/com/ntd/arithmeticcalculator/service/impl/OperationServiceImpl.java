package com.ntd.arithmeticcalculator.service.impl;

import com.ntd.arithmeticcalculator.model.entity.Operation;
import com.ntd.arithmeticcalculator.repository.OperationRepository;
import com.ntd.arithmeticcalculator.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    @Override
    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    @Override
    public Optional<Operation> update(Long id, Operation operationDetails) {
        return operationRepository.findById(id)
                .map(operation -> {
                    operation.setCost(operationDetails.getCost());
                    operation.setRecords(operationDetails.getRecords());
                    operation.setType(operationDetails.getType());
                    return operationRepository.save(operation);
                });
    }

    @Override
    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }
}