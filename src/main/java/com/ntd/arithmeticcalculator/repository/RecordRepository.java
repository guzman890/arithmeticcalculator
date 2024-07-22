package com.ntd.arithmeticcalculator.repository;

import com.ntd.arithmeticcalculator.model.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Page<Record> findAll(Pageable pageable);

}