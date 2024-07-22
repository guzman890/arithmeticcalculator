package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.model.OperationRequest;
import com.ntd.arithmeticcalculator.model.OperationType;
import com.ntd.arithmeticcalculator.model.entity.Operation;
import com.ntd.arithmeticcalculator.model.entity.Record;
import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import com.ntd.arithmeticcalculator.service.OperationService;
import com.ntd.arithmeticcalculator.service.RecordService;
import com.ntd.arithmeticcalculator.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecordControllerTest {

    @Mock
    private RecordService recordService;

    @Mock
    private UserService userService;

    @Mock
    private OperationService operationService;

    @InjectMocks
    private RecordController recordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test para obtener todos los registros")
    public void testGetAllRecords() {
        Record record1 = new Record();
        Record record2 = new Record();
        List<Record> expectedRecords = Arrays.asList(record1, record2);
        when(recordService.findAll()).thenReturn(expectedRecords);

        ResponseEntity<List<Record>> response = recordController.getAllRecords();

        assertEquals(expectedRecords, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para obtener registros paginados")
    public void testGetRecordsPageable() {
        PageRequest pageable = PageRequest.of(0, 10);
        Record record1 = new Record();
        Record record2 = new Record();
        List<Record> records = Arrays.asList(record1, record2);
        Page<Record> expectedPage = new PageImpl<>(records, pageable, records.size());
        when(recordService.getRecords(pageable)).thenReturn(expectedPage);

        ResponseEntity<Page<Record>> response = recordController.getRecords(pageable);

        assertEquals(expectedPage, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para crear un registro")
    public void testCreateRecord() {
        OperationRequest operationRequest = new OperationRequest();
        operationRequest.setOperationId(1L);
        operationRequest.setUserId(1L);
        operationRequest.setValue(Arrays.asList("5", "3"));

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setCredits(100D);

        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(OperationType.ADDITION);
        operation.setCost(10D);

        Record record = new Record();
        record.setOperation(operation);
        record.setUser(user);
        record.setAmount(operation.getCost());
        record.setOperationResponse("8");
        record.setUserBalance(user.getCredits());
        record.setDate(LocalDateTime.now());

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(operationService.findById(1L)).thenReturn(Optional.of(operation));
        when(recordService.saveRecord(any(Record.class))).thenReturn(record);

        ResponseEntity<Record> response = recordController.createRecord(operationRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(record, response.getBody());
    }
}