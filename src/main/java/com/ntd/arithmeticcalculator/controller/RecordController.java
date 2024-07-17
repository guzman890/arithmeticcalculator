package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.business.operations.OperationExecutor;
import com.ntd.arithmeticcalculator.business.OperationFactory;
import com.ntd.arithmeticcalculator.model.OperationRequest;
import com.ntd.arithmeticcalculator.model.entity.Operation;
import com.ntd.arithmeticcalculator.model.entity.Record;
import com.ntd.arithmeticcalculator.model.entity.User;
import com.ntd.arithmeticcalculator.service.OperationService;
import com.ntd.arithmeticcalculator.service.RecordService;
import com.ntd.arithmeticcalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<List<Record>> getAllRecords() {
        return ResponseEntity.ok(recordService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        return recordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody OperationRequest operationRequest) {
        Long operationId = operationRequest.getOperationId();
        Long userId = operationRequest.getUserId();

        User user = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Operation operation= operationService.findById(operationId).orElseThrow(() -> new IllegalArgumentException("Operation not found"));

        OperationExecutor operationExecutor = OperationFactory.getOperation(operation.getType());
        String result = operationExecutor.execute(operationRequest.getValue());

        Record record = new Record();
        record.setOperation(operation);
        record.setUser(user);
        record.setAmount(operation.getCost());
        record.setOperationResponse(result);
        record.setUserBalance(user.getCredits());
        record.setDate(LocalDateTime.now());

        return ResponseEntity.ok(recordService.saveRecord(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record record) {
        return recordService.update(id, record)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.ok().build();
    }
}