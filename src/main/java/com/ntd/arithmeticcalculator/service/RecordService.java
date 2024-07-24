package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    RecordEntity saveRecord(RecordEntity recordEntity);
    Optional<RecordEntity> findById(Long id);
    List<RecordEntity> findAll();
    Page<RecordEntity> getRecords(Pageable pageable);
    Optional<RecordEntity> update(Long id, RecordEntity recordEntityDetails);
    void deleteRecord(Long id);
}