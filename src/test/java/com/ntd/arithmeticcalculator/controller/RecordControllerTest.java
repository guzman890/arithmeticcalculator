package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.model.dto.RecordDto;
import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
import com.ntd.arithmeticcalculator.model.entity.RecordEntity;
import com.ntd.arithmeticcalculator.model.request.OperationRequest;
import com.ntd.arithmeticcalculator.model.OperationType;
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
        RecordEntity recordEntity1 = new RecordEntity();
        recordEntity1.setOperation(new OperationEntity());
        recordEntity1.setUser(new UserEntity());
        RecordEntity recordEntity2 = new RecordEntity();
        recordEntity2.setOperation(new OperationEntity());
        recordEntity2.setUser(new UserEntity());
        List<RecordEntity> expectedRecordEntities = Arrays.asList(recordEntity1, recordEntity2);
        when(recordService.findAll()).thenReturn(expectedRecordEntities);

        ResponseEntity<List<RecordDto>> response = recordController.getAllRecords();

        assertEquals(expectedRecordEntities.size(), response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para obtener registros paginados")
    public void testGetRecordsPageable() {
        PageRequest pageable = PageRequest.of(0, 10);
        RecordEntity recordEntity1 = new RecordEntity();
        recordEntity1.setOperation(new OperationEntity());
        recordEntity1.setUser(new UserEntity());
        RecordEntity recordEntity2 = new RecordEntity();
        recordEntity2.setOperation(new OperationEntity());
        recordEntity2.setUser(new UserEntity());
        List<RecordEntity> recordEntities = Arrays.asList(recordEntity1, recordEntity2);
        Page<RecordEntity> expectedPage = new PageImpl<>(recordEntities, pageable, recordEntities.size());
        when(recordService.getRecords(pageable)).thenReturn(expectedPage);

        ResponseEntity<Page<RecordDto>> response = recordController.getRecords(pageable);

        assertEquals(expectedPage.getTotalElements(), response.getBody().getTotalElements());
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

        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setId(1L);
        operationEntity.setType(OperationType.ADDITION);
        operationEntity.setCost(10D);

        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setOperation(operationEntity);
        recordEntity.setUser(user);
        recordEntity.setAmount(operationEntity.getCost());
        recordEntity.setOperationResponse("8");
        recordEntity.setUserBalance(user.getCredits());
        recordEntity.setDate(LocalDateTime.now());

        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(operationService.findById(1L)).thenReturn(Optional.of(operationEntity));
        when(recordService.saveRecord(any(RecordEntity.class))).thenReturn(recordEntity);

        ResponseEntity<RecordDto> response = (ResponseEntity<RecordDto>) recordController.createRecord(operationRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(recordEntity.getId(), response.getBody().getId());
    }
}