package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.Record;
import java.util.List;
import java.util.Optional;

public interface RecordService {
    Record saveRecord(Record record);
    Optional<Record> findById(Long id);
    List<Record> findAll();
    Optional<Record> update(Long id, Record recordDetails);
    void deleteRecord(Long id);
}