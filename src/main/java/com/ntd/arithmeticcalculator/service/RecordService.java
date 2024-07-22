package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    Record saveRecord(Record record);
    Optional<Record> findById(Long id);
    List<Record> findAll();
    Page<Record> getRecords(Pageable pageable);
    Optional<Record> update(Long id, Record recordDetails);
    void deleteRecord(Long id);
}