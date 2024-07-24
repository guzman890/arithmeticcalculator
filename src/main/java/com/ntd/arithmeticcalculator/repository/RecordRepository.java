package com.ntd.arithmeticcalculator.repository;

import com.ntd.arithmeticcalculator.model.entity.RecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    Page<RecordEntity> findAll(Pageable pageable);

}