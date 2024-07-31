package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.business.operations.OperationExecutor;
import com.ntd.arithmeticcalculator.business.OperationFactory;
import com.ntd.arithmeticcalculator.model.dto.RecordDto;
import com.ntd.arithmeticcalculator.model.entity.RecordEntity;
import com.ntd.arithmeticcalculator.model.request.OperationRequest;
import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import com.ntd.arithmeticcalculator.service.OperationService;
import com.ntd.arithmeticcalculator.service.RecordService;
import com.ntd.arithmeticcalculator.service.UserService;
import com.ntd.arithmeticcalculator.service.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    @Autowired
    private OperationService operationService;

    @GetMapping
    public ResponseEntity<List<RecordDto>> getAllRecords() {
        List<RecordDto> records = recordService.findAll().stream()
                .map(RecordMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(records);    }

    @GetMapping("/page")
    public ResponseEntity<Page<RecordDto>> getRecords(Pageable pageable) {
        Page<RecordEntity> page = recordService.getRecords(pageable);
        Page<RecordDto> dtoPage = page.map(RecordMapper::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordDto> getRecordById(@PathVariable Long id) {
        return recordService.findById(id)
                .map(RecordMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createRecord(@RequestBody OperationRequest operationRequest) {
        try {
            Long operationId = operationRequest.getOperationId();
            Long userId = operationRequest.getUserId();

            UserEntity user = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
            OperationEntity operationEntity = operationService.findById(operationId).orElseThrow(() -> new IllegalArgumentException("Operation not found"));

            if (user.getCredits() < operationEntity.getCost()) {
                throw new IllegalArgumentException("Not enough credits");
            }

            OperationExecutor operationExecutor = OperationFactory.getOperation(operationEntity.getType());
            String result = operationExecutor.execute(operationRequest.getValue());

            RecordEntity recordEntity = new RecordEntity();
            recordEntity.setOperation(operationEntity);
            recordEntity.setUser(user);
            recordEntity.setAmount(operationEntity.getCost());
            recordEntity.setOperationResponse(result);
            recordEntity.setUserBalance(user.getCredits());
            recordEntity.setDate(LocalDateTime.now());

            user.setCredits(user.getCredits() - operationEntity.getCost());
            userService.update(user.getId(), user);

            return ResponseEntity.ok(RecordMapper.toDto(recordService.saveRecord(recordEntity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordDto> updateRecord(@PathVariable Long id, @RequestBody RecordDto recordDto) {
        return recordService.update(id, RecordMapper.toEntity(recordDto))
                .map(RecordMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.ok().build();
    }
}