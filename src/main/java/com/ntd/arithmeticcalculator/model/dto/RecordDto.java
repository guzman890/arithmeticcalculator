package com.ntd.arithmeticcalculator.model.dto;

import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordDto {
    private Long id;

    private OperationDto operation;

    private UserDto user;

    private Double amount;

    private Double userBalance;

    private String operationResponse;

    private LocalDateTime date;
}
