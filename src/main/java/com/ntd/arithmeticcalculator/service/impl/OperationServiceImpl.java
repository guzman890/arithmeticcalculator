package com.ntd.arithmeticcalculator.service.impl;

import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
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
    public OperationEntity saveOperation(OperationEntity operationEntity) {
        return operationRepository.save(operationEntity);
    }

    @Override
    public Optional<OperationEntity> findById(Long id) {
        return operationRepository.findById(id);
    }

    @Override
    public List<OperationEntity> findAll() {
        return operationRepository.findAll();
    }

    @Override
    public Optional<OperationEntity> update(Long id, OperationEntity operationEntityDetails) {
        return operationRepository.findById(id)
                .map(operation -> {
                    operation.setCost(operationEntityDetails.getCost());
                    operation.setRecordEntities(operationEntityDetails.getRecordEntities());
                    operation.setType(operationEntityDetails.getType());
                    return operationRepository.save(operation);
                });
    }

    @Override
    public void deleteOperation(Long id) {
        operationRepository.deleteById(id);
    }
}