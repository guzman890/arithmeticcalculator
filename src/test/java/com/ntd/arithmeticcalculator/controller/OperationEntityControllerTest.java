package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.model.dto.OperationDto;
import com.ntd.arithmeticcalculator.model.entity.OperationEntity;
import com.ntd.arithmeticcalculator.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OperationEntityControllerTest {

    @Mock
    private OperationService operationService;

    @InjectMocks
    private OperationController operationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test para obtener todas las operaciones")
    public void testGetAllOperations() {
        OperationEntity operationEntity1 = new OperationEntity();
        OperationEntity operationEntity2 = new OperationEntity();
        List<OperationEntity> expectedOperationEntities = Arrays.asList(operationEntity1, operationEntity2);
        when(operationService.findAll()).thenReturn(expectedOperationEntities);

        ResponseEntity<List<OperationDto>> response = operationController.getAllOperations();

        assertEquals(expectedOperationEntities.size(), response.getBody().size());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para crear una operación")
    public void testCreateOperation() {
        OperationEntity operationEntity = new OperationEntity();
        when(operationService.saveOperation(any(OperationEntity.class))).thenReturn(operationEntity);

        ResponseEntity<OperationDto> response = operationController.createOperation(new OperationDto());

        assertEquals(operationEntity.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para obtener una operación por ID")
    public void testGetOperationById() {
        OperationEntity operationEntity = new OperationEntity();
        when(operationService.findById(1L)).thenReturn(Optional.of(operationEntity));

        ResponseEntity<OperationDto> response = operationController.getOperationById(1L);

        assertEquals(operationEntity.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para actualizar una operación")
    public void testUpdateOperation() {
        OperationEntity operationEntity = new OperationEntity();
        when(operationService.update(eq(1L), any(OperationEntity.class))).thenReturn(Optional.of(operationEntity));

        ResponseEntity<OperationDto> response = operationController.updateOperation(1L, new OperationDto());

        assertEquals(operationEntity.getId(), response.getBody().getId());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para eliminar una operación")
    public void testDeleteOperation() {
        doNothing().when(operationService).deleteOperation(1L);

        ResponseEntity<Void> response = operationController.deleteOperation(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(operationService).deleteOperation(1L);
    }
}