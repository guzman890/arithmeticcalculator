package com.ntd.arithmeticcalculator.service.mapper;

import com.ntd.arithmeticcalculator.model.dto.RecordDto;
import com.ntd.arithmeticcalculator.model.entity.RecordEntity;

public class RecordMapper {

    public static RecordDto toDto(RecordEntity recordEntity) {
        RecordDto recordDto = new RecordDto();
        recordDto.setId(recordEntity.getId());
        recordDto.setOperation(OperationMapper.toDto(recordEntity.getOperation()));
        recordDto.setUser(UserMapper.toDto(recordEntity.getUser()));
        recordDto.setAmount(recordEntity.getAmount());
        recordDto.setUserBalance(recordEntity.getUserBalance());
        recordDto.setOperationResponse(recordEntity.getOperationResponse());
        recordDto.setDate(recordEntity.getDate());
        return recordDto;
    }

    public static RecordEntity toEntity(RecordDto recordDto) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setId(recordDto.getId());
        recordEntity.setOperation(OperationMapper.toEntity(recordDto.getOperation()));
        recordEntity.setUser(UserMapper.toEntity(recordDto.getUser()));
        recordEntity.setAmount(recordDto.getAmount());
        recordEntity.setUserBalance(recordDto.getUserBalance());
        recordEntity.setOperationResponse(recordDto.getOperationResponse());
        recordEntity.setDate(recordDto.getDate());
        return recordEntity;
    }
}