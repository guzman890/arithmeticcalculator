package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.service.mapper.OperationMapper;
import com.ntd.arithmeticcalculator.model.dto.OperationDto;
import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
import com.ntd.arithmeticcalculator.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        List<OperationDto> operations = operationService.findAll().stream()
                .map(OperationMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(operations);
    }

    @PostMapping
    public ResponseEntity<OperationDto> createOperation(@RequestBody OperationDto operationDto) {
        OperationEntity operationEntity = OperationMapper.toEntity(operationDto);
        OperationEntity savedOperation = operationService.saveOperation(operationEntity);
        return ResponseEntity.ok(OperationMapper.toDto(savedOperation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperationDto> getOperationById(@PathVariable Long id) {
        return operationService.findById(id)
                .map(OperationMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<OperationDto> updateOperation(@PathVariable Long id, @RequestBody OperationDto operationDto) {
        OperationEntity operationEntity = OperationMapper.toEntity(operationDto);
        return operationService.update(id, operationEntity)
                .map(OperationMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
        return ResponseEntity.ok().build();
    }
}