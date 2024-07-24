package com.ntd.arithmeticcalculator.service.mapper;

import com.ntd.arithmeticcalculator.model.dto.OperationDto;
import com.ntd.arithmeticcalculator.model.entity.OperationEntity;

public class OperationMapper {

    public static OperationDto toDto(OperationEntity operationEntity) {
        OperationDto operationDto = new OperationDto();
        operationDto.setId(operationEntity.getId());
        operationDto.setLabel(operationEntity.getLabel());
        operationDto.setType(operationEntity.getType());
        operationDto.setSymbol(operationEntity.getSymbol());
        operationDto.setCost(operationEntity.getCost());
        return operationDto;
    }

    public static OperationEntity toEntity(OperationDto operationDto) {
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setId(operationDto.getId());
        operationEntity.setLabel(operationDto.getLabel());
        operationEntity.setType(operationDto.getType());
        operationEntity.setSymbol(operationDto.getSymbol());
        operationEntity.setCost(operationDto.getCost());
        return operationEntity;
    }
}
