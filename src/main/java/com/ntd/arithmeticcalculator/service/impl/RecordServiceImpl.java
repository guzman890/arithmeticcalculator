package com.ntd.arithmeticcalculator.service.impl;

import com.ntd.arithmeticcalculator.model.entity.Record;
import com.ntd.arithmeticcalculator.repository.RecordRepository;
import com.ntd.arithmeticcalculator.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public Optional<Record> findById(Long id) {
        return recordRepository.findById(id);
    }

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Optional<Record> update(Long id, Record recordDetails) {
        return recordRepository.findById(id)
                .map(record -> {
                    record.setAmount(recordDetails.getAmount());
                    record.setUserBalance(recordDetails.getUserBalance());
                    record.setOperationResponse(recordDetails.getOperationResponse());
                    record.setDate(recordDetails.getDate());
                    return recordRepository.save(record);
                });
    }

    @Override
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}