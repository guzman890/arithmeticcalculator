package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.model.entity.Operation;
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

public class OperationControllerTest {

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
        Operation operation1 = new Operation();
        Operation operation2 = new Operation();
        List<Operation> expectedOperations = Arrays.asList(operation1, operation2);
        when(operationService.findAll()).thenReturn(expectedOperations);

        ResponseEntity<List<Operation>> response = operationController.getAllOperations();

        assertEquals(expectedOperations, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para crear una operación")
    public void testCreateOperation() {
        Operation operation = new Operation();
        when(operationService.saveOperation(any(Operation.class))).thenReturn(operation);

        ResponseEntity<Operation> response = operationController.createOperation(new Operation());

        assertEquals(operation, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para obtener una operación por ID")
    public void testGetOperationById() {
        Operation operation = new Operation();
        when(operationService.findById(1L)).thenReturn(Optional.of(operation));

        ResponseEntity<Operation> response = operationController.getOperationById(1L);

        assertEquals(operation, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para actualizar una operación")
    public void testUpdateOperation() {
        Operation operation = new Operation();
        when(operationService.update(eq(1L), any(Operation.class))).thenReturn(Optional.of(operation));

        ResponseEntity<Operation> response = operationController.updateOperation(1L, new Operation());

        assertEquals(operation, response.getBody());
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