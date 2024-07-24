package com.ntd.arithmeticcalculator.model.dto;

import com.ntd.arithmeticcalculator.model.OperationType;
import lombok.Data;

@Data
public class OperationDto {
    private Long id;
    private String label;
    private OperationType type;
    private String symbol;
    private Double cost;
}