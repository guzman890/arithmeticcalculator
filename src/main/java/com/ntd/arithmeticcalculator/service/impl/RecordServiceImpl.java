package com.ntd.arithmeticcalculator.service.impl;

import com.ntd.arithmeticcalculator.model.entity.RecordEntity;
import com.ntd.arithmeticcalculator.repository.RecordRepository;
import com.ntd.arithmeticcalculator.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public RecordEntity saveRecord(RecordEntity recordEntity) {
        return recordRepository.save(recordEntity);
    }

    @Override
    public Optional<RecordEntity> findById(Long id) {
        return recordRepository.findById(id);
    }

    @Override
    public List<RecordEntity> findAll() {
        return recordRepository.findAll();
    }

    public Page<RecordEntity> getRecords(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @Override
    public Optional<RecordEntity> update(Long id, RecordEntity recordEntityDetails) {
        return recordRepository.findById(id)
                .map(record -> {
                    record.setAmount(recordEntityDetails.getAmount());
                    record.setUserBalance(recordEntityDetails.getUserBalance());
                    record.setOperationResponse(recordEntityDetails.getOperationResponse());
                    record.setDate(recordEntityDetails.getDate());
                    return recordRepository.save(record);
                });
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}